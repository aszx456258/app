package com.example.lee.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.media.RingtoneManager.TYPE_ALARM;

/**
 * Created by lenovo on 2017/11/23.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        Intent alaramIntent = new Intent(context, AlarmSettingAgain.class);
//        alaramIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(alaramIntent);
        Toast.makeText(context, "你設定的鬧鈴時間到了", Toast.LENGTH_LONG).show();
//        Log.d("ddddd","dododododod");
        Uri alarmUri= RingtoneManager.getDefaultUri(TYPE_ALARM);
//        // 取得鈴聲
        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        // 播放鈴聲
        ringtone.play();
//        // 定義一個計時器程序來停止播放鈴聲
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ringtone.stop();
            }
        };
        Timer timer = new Timer();
//        // 5秒後停止鈴聲
        timer.schedule(task, 5000);
    }
}