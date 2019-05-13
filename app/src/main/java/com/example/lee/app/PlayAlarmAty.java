package com.example.lee.app;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lee.hi.R;

/**
 * Created by lenovo on 2017/11/25.
 */

public class PlayAlarmAty extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRepeatinContentView(R.layout.alarm_player_aty);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();

    }

    private MediaPlayer mp;
    //音乐播放器
}

