package com.example.lee.app;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Calendar;

public class  AlarmData {

    //闹钟所要响起的时间
    @RequiresApi(api = Build.VERSION_CODES.N)
    public AlarmData(long time) {
        this.time = time;

        date = Calendar.getInstance();
        date.setTimeInMillis(time);

        timeLable = String.format("%d月%d日 %d:%d",
                date.get(Calendar.MONTH) + 1,//getMonth的返回值是从0开始的
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE));
    }

    public long getTime() {
        return time;
    }

    public String getTimeLable() {
        return timeLable;
    }

    @Override
    public String toString() {
        return getTimeLable();
    }

    public int getId() {
        return (int) (getTime() / 1000 / 60);//精确到分钟，getTime为毫秒
    }


    //获取时间的标签
    private String timeLable = "";
    private long time = 0;
    private Calendar date;
}
