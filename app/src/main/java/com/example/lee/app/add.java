package com.example.lee.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lee.hi.R;

import java.util.ArrayList;
import java.util.Calendar;

public class add extends Activity {
    private Button b_date,save,med;
    TextView data,med_name;
    Calendar c = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private ArrayList<GoodsEntity> goodsEntityList = Fragment1.goodsEntityList;
    public View view = Fragment1.view;
    public RecyclerView mCollectRecyclerView = Fragment1.mCollectRecyclerView;
    public CollectRecycleAdapter mCollectRecyclerAdapter = Fragment1.mCollectRecyclerAdapter;
    public Context context = Fragment1.context;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add);
        c.get(Calendar.YEAR);
        c.get(Calendar.MONTH);
        c.get(Calendar.DAY_OF_MONTH);
        b_date = (Button) findViewById(R.id.btt);
        data = (TextView)findViewById(R.id.date);
        med = (Button)findViewById(R.id.med);
        med_name = (TextView)findViewById(R.id.med_name);
        SharedPreferences prefs =getApplicationContext().getSharedPreferences("drug",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String longtime = prefs.getString("longtime","null");
        String mn = prefs.getString("med_name","null");
        if(longtime.equals("null")){
            data.setText("時間");
        }
        else
        {
            data.setText(longtime);
        }
        if(mn.equals("null")){
            med_name.setText("藥物種類");
        }
        else{
            med_name.setText(mn);
        }
        b_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setAlarmTime();
            }
        });
        save = (Button) findViewById(R.id.set);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Activity myActivity=(Activity)(v.getContext()); // all views have a reference to their context
                SharedPreferences prefs =myActivity.getSharedPreferences("drug",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                int plus = prefs.getInt("plus",-1);
                String medicines = prefs.getString("med_name","null");
                if(plus==0 && (!medicines.equals("null"))){
                    int drug = prefs.getInt("can",0);
                    int count = prefs.getInt("count",0);
                    String longTime = prefs.getString("longtime","123");
                    Intent intent = new Intent();
                    intent.setAction("com.westsoft.alarmtime.ACTION");// Activity
                    PendingIntent pi = PendingIntent.getActivity(add.this, drug, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            c.getTimeInMillis(), pi);

                    Log.i("TimeInMillis", c.getTimeInMillis()+"");
                    // 显示闹铃设置成功的提示信息
                    Toast.makeText(add.this,"鬧鐘設定成功", Toast.LENGTH_SHORT).show();
                    saveAlarmList(longTime,drug,count,medicines);
//                    GoodsEntity goodsEntity=new GoodsEntity();
//                    String index = Integer.toString(count-1);
//                    goodsEntity.setGoodsName(prefs.getString("name"+index,""));
//                    goodsEntity.setGoodsPrice("藥");
//                    goodsEntity.setPosition(prefs.getInt("position"+index,0));
//                    goodsEntityList.add(goodsEntity);
                    editor.putInt("plus",1);
//                    editor.putInt("already",1);
                    editor.putString("longtime","null");
                    editor.commit();
                    initRecyclerView();
                }
                else if(medicines.equals("null")){
                    Toast.makeText(add.this,"還未設定藥物", Toast.LENGTH_SHORT).show();
                }
            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] listItems = {"血糖藥", "心臟藥", "血壓藥", "維他命", "腸胃藥"};

                AlertDialog.Builder builder = new AlertDialog.Builder(add.this);
                builder.setTitle("要吃什麼藥");

                int checkedItem = 0; //this will checked the item when user open the dialog
                final int[] which_item = {0};
                builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        med_name.setText(listItems[which]);
//                        Toast.makeText(add.this, "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                        which_item[0] = which;
                    }
                });

                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences prefs =getApplication().getSharedPreferences("drug",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("med_name",listItems[which_item[0]]);
                        med_name.setText(listItems[which_item[0]]);
                        editor.commit();
//                        dialog.dismiss();
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
//    private void setAlarmDate() {
//
//        final Calendar currentDate = Calendar.getInstance();
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year,
//                                  int monthOfYear, int dayOfMonth) {
//
//                c.set(Calendar.YEAR, year);
//                c.set(Calendar.MONTH, monthOfYear);
//                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                setAlarmTime(year,monthOfYear,dayOfMonth);
//            }
//        }, currentDate.get(Calendar.YEAR),
//                currentDate.get(Calendar.MONTH),
//                currentDate.get(Calendar.DAY_OF_MONTH));
//
//        datePickerDialog.show();
//
//    }
//    private void setDialogBroadcast(){
//        View viewDialogBroadcast;       //使用view来接入方法写出的dialog，方便相关初始化
//        LayoutInflater inflater;        //引用自定义dialog布局
//        inflater=LayoutInflater.from(context);
//        viewDialogBroadcast = (LinearLayout) inflater.inflate(R.layout.dialog, null);                                           //那个layout就是我们可以dialog自定义的布局啦
//        final RadioGroup groupBroadcast = (RadioGroup) viewDialogBroadcast.findViewById(R.id.groupBroadcast);
//        final RadioButton rbtn_BroadcastClose = (RadioButton) viewDialogBroadcast.findViewById(R.id.rbtn_BroadcastClose);
//        final RadioButton rbtn_BroadcastFifteen = (RadioButton) viewDialogBroadcast.findViewById(R.id.rbtn_BroadcastFifteen);
//        final RadioButton rbtn_BroadcastThirty = (RadioButton) viewDialogBroadcast.findViewById(R.id.rbtn_BroadcastThirty);
//        final RadioButton rbtn_BroadcastFourty = (RadioButton) viewDialogBroadcast.findViewById(R.id.rbtn_BroadcastFourty);
//        final RadioButton rbtn_BroadcastMinute = (RadioButton) viewDialogBroadcast.findViewById(R.id.rbtn_BroadcastMinute);
//        groupBroadcast.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId==rbtn_BroadcastClose.getId()){
//
//                }
//                else if (checkedId==rbtn_BroadcastFifteen.getId()){
//
//                }
//                else if (checkedId==rbtn_BroadcastThirty.getId()){
//
//                }
//                else if (checkedId==rbtn_BroadcastFourty.getId()){
//
//                }
//                else if (checkedId==rbtn_BroadcastMinute.getId()){
//
//                }
//
//            }
//        });
//    }

    /**
     * 设置闹铃时间
     * */

    private void setAlarmTime() {
        Calendar currentTime = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来。
        new TimePickerDialog(this, 0, // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay,
                                          int minute) {
                        // 指定启动AlarmActivity组件
//                        Intent intent = new Intent();
//                        intent.setAction("com.westsoft.alarmtime.ACTION");// Activity

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("drug", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        int drug = pref.getInt("can", 0);
                        int count = pref.getInt("count", 0);
                        int plus = pref.getInt("plus", 1);
                        if(plus==1){
                            drug+=1;
                            count+=1;
                        }
                        // 创建PendingIntent对象
//                        PendingIntent pi = PendingIntent.getActivity(add.this, drug, intent, 0);
//                        Log.i("TimeInMillis", "TimeInMillis_1"+c.getTimeInMillis()+"");
                        // 根据用户选择时间来设置Calendar对象
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);

//                        String longTime = (monthOfYear+1)+"月"+dayOfMonth+"日"+" "+hourOfDay
//                                + ":" + minute;
                        String longTime = hourOfDay + ":" + minute;
                        data.setText(longTime);
                        editor.putInt("plus",0);
                        editor.putInt("count",count);
                        editor.putInt("can",drug);
                        editor.putString("longtime",longTime);
                        editor.commit();
                        //2016-10-25 10:44:53

                        // 设置AlarmManager将在Calendar对应的时间启动指定组件
                        // 设置闹钟，当前时间就唤醒
//                        AlarmManager alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
//                        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
//
//
//                        Log.i("TimeInMillis", c.getTimeInMillis()+"");
//                        // 显示闹铃设置成功的提示信息
//                        Toast.makeText(add.this,"闹铃设置成功啦", Toast.LENGTH_SHORT).show();
//                        saveAlarmList(longTime,drug,count);
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                .get(Calendar.MINUTE), true).show();
    }
    private void saveAlarmList(String longtime,int drug,int count,String med){
        int inde = Fragment1.inde;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("drug", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        GoodsEntity goodsEntity=new GoodsEntity();
        goodsEntity.setGoodsName(longtime);
        goodsEntity.setGoodsPrice(med);
        goodsEntity.setPosition(drug);
        goodsEntity.setIndex(inde);
        Fragment1.inde += 1;
        goodsEntityList.add(goodsEntity);
        editor.putString("name"+Integer.toString(drug),longtime);
        editor.putString("med"+Integer.toString(drug),med);
        editor.putInt("position"+Integer.toString(drug),drug);
        editor.putInt("count",count);
        editor.putInt("can",drug);
        editor.putString("med_name","null");
        editor.commit();
    }
    public void initRecyclerView() {
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
                        SharedPreferences prefs =getApplication().getSharedPreferences("drug",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        int drug = prefs.getInt("can",0);
                        int plus = prefs.getInt("plus",1);
                        if(plus == 0){
                            drug-=1;
                        }
                        for(int i = data.index+1;i<Fragment1.inde;i++){
                            int will = goodsEntityList.get(i).getIndex()-1;
                            goodsEntityList.get(i).setIndex(will);
                        }
                        editor.putString("name"+Integer.toString(data.getPosition()),"null");
                        editor.commit();
                        Fragment1.inde-=1;
                        goodsEntityList.remove(data.index);
                        initRecyclerView();
                    }
                }).show();
//                Toast.makeText(context,"我是"+data.getGoodsPrice()+data.getIndex(),Toast.LENGTH_SHORT).show();
//                goodsEntityList.remove(data.getIndex());
            }
        });
    }
}