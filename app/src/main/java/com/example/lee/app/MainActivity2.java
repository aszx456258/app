package com.example.lee.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lee.hi.R;

public class MainActivity2 extends Activity {

    private Button bt;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);
        bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity2.this , MainActivity.class);
                        startActivity(intent);
                    }


                }
        );
    }

}
