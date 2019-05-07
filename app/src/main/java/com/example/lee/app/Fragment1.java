package com.example.lee.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lee.hi.R;

import java.util.ArrayList;

/**
 * Created by Carson_Ho on 16/5/23.
 */
public class Fragment1 extends Fragment

{
    private View view;
    public RecyclerView mCollectRecyclerView;
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    private CollectRecycleAdapter mCollectRecyclerAdapter;
    private Button img;
//    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_item1, null);
        view = inflater.inflate(R.layout.fragment_item1, container, false);
        //对recycleview进行配置
        initRecyclerView();
        initData();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.clock);
        img = (Button) view.findViewById(R.id.add);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }
    private void initData() {
        GoodsEntity goodsEntity=new GoodsEntity();
        goodsEntity.setGoodsName("7:00");
        goodsEntity.setGoodsPrice("胃藥");
        goodsEntityList.add(goodsEntity);
        GoodsEntity goodsEntity2=new GoodsEntity();
        goodsEntity2.setGoodsName("12:00");
        goodsEntity2.setGoodsPrice("血壓藥");
        goodsEntityList.add(goodsEntity2);
        GoodsEntity goodsEntity3=new GoodsEntity();
        goodsEntity3.setGoodsName("14:00");
        goodsEntity3.setGoodsPrice("血糖藥");
        goodsEntityList.add(goodsEntity3);
        GoodsEntity goodsEntity4=new GoodsEntity();
        goodsEntity4.setGoodsName("20:30");
        goodsEntity4.setGoodsPrice("維他命");
        goodsEntityList.add(goodsEntity4);
    }
    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.clock);
        //创建adapter
        mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), goodsEntityList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
    }
}
