package com.example.lee.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lee.hi.R;

public class MainActivity2 extends Activity {

    private Button bt;
    private EditText edt;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);
        SharedPreferences prefs =getApplicationContext().getSharedPreferences("drug", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        bt = (Button)findViewById(R.id.button2);
        edt = (EditText)findViewById(R.id.txv);
        edt.setText(prefs.getString("yourname","設置名稱"));
        int my_first;
        my_first = prefs.getInt("my_first",0);
        if(my_first==1){
            Intent intent = new Intent();
            intent.setClass(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        }
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences prefs =getApplicationContext().getSharedPreferences("drug", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        if(edt.getText().toString().equals("設置名稱")){
                            Toast.makeText(MainActivity2.this,"還未設定名字", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            editor.putString("yourname",edt.getText().toString());
                            editor.putInt("my_first",1);
                            editor.commit();
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }


                }
        );
    }

}
