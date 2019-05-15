package com.example.lee.app;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.lee.hi.R;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 16/5/23.
 */
//public class Fragment1 extends Fragment
//{
//    private View view;
//    public RecyclerView mCollectRecyclerView;
//    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
//    private CollectRecycleAdapter mCollectRecyclerAdapter;
//    private Button img;
////    private RecyclerView recyclerView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.fragment_item1, null);
//        view = inflater.inflate(R.layout.fragment_item1, container, false);
//        //对recycleview进行配置
//        initRecyclerView();
//        initData();
//        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.clock);
//        img = (Button) view.findViewById(R.id.add);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//
//        return view;
//    }
//    private void initData() {
//        GoodsEntity goodsEntity=new GoodsEntity();
//        goodsEntity.setGoodsName("7:00");
//        goodsEntity.setGoodsPrice("胃藥");
//        goodsEntityList.add(goodsEntity);
//        GoodsEntity goodsEntity2=new GoodsEntity();
//        goodsEntity2.setGoodsName("12:00");
//        goodsEntity2.setGoodsPrice("血壓藥");
//        goodsEntityList.add(goodsEntity2);
//        GoodsEntity goodsEntity3=new GoodsEntity();
//        goodsEntity3.setGoodsName("14:00");
//        goodsEntity3.setGoodsPrice("血糖藥");
//        goodsEntityList.add(goodsEntity3);
//        GoodsEntity goodsEntity4=new GoodsEntity();
//        goodsEntity4.setGoodsName("20:30");
//        goodsEntity4.setGoodsPrice("維他命");
//        goodsEntityList.add(goodsEntity4);
//    }
//    private void initRecyclerView() {
//        //获取RecyclerView
//        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.clock);
//        //创建adapter
//        mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), goodsEntityList);
//        //给RecyclerView设置adapter
//        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
//        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
//        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
//        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        //设置item的分割线
//        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
//    }
//}
public class Fragment1 extends Fragment
{
    private static  final  String KEY_ALARM_LIST="alarmList";
    public static View view;
    public static RecyclerView mCollectRecyclerView;
    public static ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    public static CollectRecycleAdapter mCollectRecyclerAdapter;
    private Button img,f5;
    public static int inde = 0;
    private ArrayAdapter<AlarmData> adapter;
    private AlarmManager alarmManager;
    int count_all=0;
    public static Context context = null;
    public static int re_last=8888;
//    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_item1, null);
        view = inflater.inflate(R.layout.fragment_item1, container, false);
        context = getActivity();
        //对recycleview进行配置
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.clock);
        final SharedPreferences pref = this.getActivity().getSharedPreferences("drug",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        img = (Button) view.findViewById(R.id.add);
//        f5 = (Button)view.findViewById(R.id.re);
        int drug = pref.getInt("can",0);
        if(drug>0){
            initData();
            initRecyclerView();
//            count_all = count;
        }
        img.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity() , add.class);
                        startActivity(intent);
//                        Activity myActivity=(Activity)(v.getContext()); // all views have a reference to their context
//                        SharedPreferences prefs =myActivity.getSharedPreferences("drug",Context.MODE_PRIVATE);
//                        int count = pref.getInt("count",0);
//                        if(count>count_all){
//                            GoodsEntity goodsEntity=new GoodsEntity();
//                            String index = Integer.toString(count-1);
//                            goodsEntity.setGoodsName(pref.getString("name"+index,""));
//                            goodsEntity.setGoodsPrice("藥");
//                            goodsEntity.setPosition(pref.getInt("position"+index,0));
//                            goodsEntityList.add(goodsEntity);
//                            count_all=count;
//                        }
//                        initRecyclerView();

                    }


                }
        );
//        mCollectRecyclerView.setOnLongClickListener();
//        f5.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Activity myActivity=(Activity)(v.getContext()); // all views have a reference to their context
//                        SharedPreferences prefs =myActivity.getSharedPreferences("drug",Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs.edit();
//                        int count = pref.getInt("count",0);
//                        int already = pref.getInt("already",0);
//                        if(count>count_all && already == 1){
//                            GoodsEntity goodsEntity=new GoodsEntity();
//                            String index = Integer.toString(count-1);
//                            goodsEntity.setGoodsName(pref.getString("name"+index,""));
//                            goodsEntity.setGoodsPrice("藥");
//                            goodsEntity.setPosition(pref.getInt("position"+index,0));
//                            goodsEntityList.add(goodsEntity);
//                            count_all=count;
//                            editor.putInt("already",0);
//                            editor.commit();
//                            initRecyclerView();
//                        }
//
//                    }
//
//
//                }
//        );
        return view;
    }

    private void initData() {
//        GoodsEntity goodsEntity=new GoodsEntity();
//        goodsEntity.setGoodsName("7:00");
//        goodsEntity.setGoodsPrice("胃藥");
//        goodsEntityList.add(goodsEntity);
        SharedPreferences pref = this.getActivity().getSharedPreferences("drug",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int drug = pref.getInt("can",0);
        Log.d("drug",Integer.toString(drug));
        int done = pref.getInt("do",0);
        if(done == 0){
            int plus = pref.getInt("plus",1);
            if(plus==0){drug=drug-1;}
            for (int i =0;i<drug;i++){
                GoodsEntity goodsEntity=new GoodsEntity();
                String index = Integer.toString(i+1);
                String name = pref.getString("name"+index,"null");
                if(!name.equals("null")) {
                    goodsEntity.setGoodsName(name);
                    goodsEntity.setGoodsPrice(pref.getString("med"+index,"null"));
                    goodsEntity.setPosition(pref.getInt("position" + index, 0));
                    goodsEntity.setIndex(inde);
                    inde+=1;
                    goodsEntityList.add(goodsEntity);
                }
            }
            editor.putInt("do",1);
            editor.commit();
        }
        else if(done==1){
            editor.putInt("do",0);
            editor.commit();
        }
        else if(done==2){
            editor.putInt("do",1);
            editor.commit();
        }
    }
    public static void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.clock);
        //创建adapter
        mCollectRecyclerAdapter = new CollectRecycleAdapter(context, goodsEntityList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new CollectRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final GoodsEntity data) {
                //此處進行監聽事件的業務處
                new AlertDialog.Builder(context).setTitle("確認刪除").setMessage("是否刪除"+data.getGoodsPrice()+"鬧鐘").setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"刪除"+data.getGoodsPrice()+data.getIndex(),Toast.LENGTH_SHORT).show();
//                       Toast.makeText(context,"刪除"+data.getGoodsPrice()+data.getIndex(),Toast.LENGTH_SHORT).show();
//                        SharedPreferences prefs =getApplication().getSharedPreferences("drug",Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs.edit();
//                        int drug = prefs.getInt("can",0);
//                        int plus = prefs.getInt("plus",1);
//                        if(plus == 0){
//                            drug-=1;
//                        }
//                        for(int i = data.index+1;i<Fragment1.inde;i++){
//                            int will = goodsEntityList.get(i).getIndex()-1;
//                            goodsEntityList.get(i).setIndex(will);
//                        }
//                        editor.putString("name"+Integer.toString(data.getPosition()),"null");
//                        editor.commit();
//                        Fragment1.inde-=1;
//                        goodsEntityList.remove(data.index);
//                        initRecyclerView();
//                        initRecyclerView();
                    }
                }).show();
//                Toast.makeText(context,"我是"+data.getGoodsPrice()+data.getIndex(),Toast.LENGTH_SHORT).show();
//                goodsEntityList.remove(data.getIndex());
            }
        });
    }


}
