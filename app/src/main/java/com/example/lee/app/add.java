package com.example.lee.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lee.hi.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class add extends Activity {
    private Button b_date;
    Calendar c = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add);
        c.get(Calendar.YEAR);
        c.get(Calendar.MONTH);
        c.get(Calendar.DAY_OF_MONTH);
        b_date = (Button) findViewById(R.id.btt);
        b_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setAlarmDate();
            }
        });
    }

    private void setAlarmDate() {

        final Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setAlarmTime(year,monthOfYear,dayOfMonth);
            }
        }, currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    /**
     * 设置闹铃时间
     * */
    private void setAlarmTime(final int year,
                              final int monthOfYear, final int dayOfMonth) {
        Calendar currentTime = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来。
        new TimePickerDialog(this, 0, // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay,
                                          int minute) {
                        // 指定启动AlarmActivity组件
                        Intent intent = new Intent();
                        intent.setAction("com.westsoft.alarmtime.ACTION");// Activity

                        // 创建PendingIntent对象
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("drug", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        int drug = pref.getInt("can", -1);
                        int count = pref.getInt("count", 0);
                        int plus = pref.getInt("plus", 1);
                        if(plus==1){
                            drug+=1;
                        }
                        count+=1;
                        PendingIntent pi = PendingIntent.getActivity(add.this, drug, intent, 0);
                        Log.i("TimeInMillis", "TimeInMillis_1"+c.getTimeInMillis()+"");
                        // 根据用户选择时间来设置Calendar对象
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);

                        String longTime = (monthOfYear+1)+"月"+dayOfMonth+"日"+" "+hourOfDay
                                + ":" + minute;
                        //2016-10-25 10:44:53

                        // 设置AlarmManager将在Calendar对应的时间启动指定组件
                        // 设置闹钟，当前时间就唤醒
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,
                                c.getTimeInMillis(), pi);


                        Log.i("TimeInMillis", c.getTimeInMillis()+"");
                        // 显示闹铃设置成功的提示信息
                        Toast.makeText(add.this,"闹铃设置成功啦", Toast.LENGTH_SHORT).show();
                        saveAlarmList(longTime,drug,count);
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                .get(Calendar.MINUTE), true).show();
    }
    private void saveAlarmList(String longtime,int drug,int count){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("drug", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        GoodsEntity goodsEntity=new GoodsEntity();
        goodsEntity.setGoodsName(pref.getString("name"+Integer.toString(drug),""));
        goodsEntity.setGoodsPrice("藥");
        goodsEntity.setPosition(pref.getInt("position"+Integer.toString(drug),0));
        goodsEntityList.add(goodsEntity);
        editor.putString("name"+Integer.toString(drug),longtime);
        editor.putString("med"+Integer.toString(drug),"藥");
        editor.putInt("position"+Integer.toString(drug),drug);
        editor.putInt("count",count);
        editor.putInt("can",drug);
        editor.commit();
    }
}