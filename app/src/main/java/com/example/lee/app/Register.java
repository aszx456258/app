package com.example.lee.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lee.hi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button btnregister;
    private EditText edtemail,edtpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        setTitle("註冊");
        findviewById();
    }

    private void findviewById() {
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        btnregister = (Button)findViewById(R.id.btnregister);
        btnregister.setOnClickListener(registerListener);
    }

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnregister){
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()){
                                    new AlertDialog.Builder(Register.this)
                                            .setMessage("註冊成功")
                                            .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }

                                            })
                                            .show();

                                }
                                else {
                                    new AlertDialog.Builder(Register.this)
                                            .setMessage("註冊失敗")
                                            .setPositiveButton("確定",null)
                                            .show();
                                }
                            }
                        });

            }
        }
    };
}