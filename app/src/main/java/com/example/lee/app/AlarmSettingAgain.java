//package com.example.lee.app;
//
//import android.app.Activity;
//import android.media.RingtoneManager;
//import android.os.Bundle;
//import android.view.Window;
//import android.view.WindowManager;
//
//public class AlarmSettingAgain extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide title
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        winParams.flags |= (WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//
//
//    }
//
//    private void startMedia() {
//        try {
//            mp.setDataSource(this,
//                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)); //铃声类型为默认闹钟铃声
//            mp.prepare();
//            mp.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
