//package com.example.lee.app;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.lee.hi.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class login extends AppCompatActivity {
//
//    private Button btnlogin,btnregister;
//    private EditText edtemail,edtpassword;
//
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.log_in);
//
//        findviewById();
//
//        mAuth = FirebaseAuth.getInstance();
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d("TAG",user.getUid());
////                    startActivity(new Intent(login.this,ShowData.class));
//                } else {
//                    // User is signed out
//                    Log.d("Tag","user ==null");
//                }
//                // ...
//            }
//        };
//    }
//    private View.OnClickListener btnListener = new View.OnClickListener(){
//
//        @Override
//        public void onClick(View view) {
//            if (view.getId()==R.id.btnlogin){
//                String email = edtemail.getText().toString();
//                String password = edtpassword.getText().toString();
//                Log.d("TAG",email);
//                Log.d("TAG",password);
//                mAuth.signInWithEmailAndPassword(email,password)
//                        .addOnCompleteListener(new OnCompleteListener() {
//                            @Override
//                            public void onComplete(@NonNull Task task) {
//                                    if (task.isSuccessful()){
//                                    Log.d("TAG","登入成功");
//                                    Intent intent = new Intent();
//                                    intent.setClass(login.this , MainActivity.class);
//                                    startActivity(intent);
//                                }
//                                else{
//                                    Log.d("TAG","登入失敗");
//                                }
//                            }
//                        });
//            }
//            if (view.getId()==R.id.btnregister){
//                startActivity(new Intent(login.this,Register.class));
//            }
//        }
//    };
//
//    private void findviewById() {
//        btnlogin = (Button)findViewById(R.id.btnlogin);
//        btnregister = (Button)findViewById(R.id.btnregister);
//        edtemail = (EditText)findViewById(R.id.edtemail);
//        edtpassword = (EditText)findViewById(R.id.edtpassword);
//        btnlogin.setOnClickListener(btnListener);
//        btnregister.setOnClickListener(btnListener);
//
//    }
//
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//}
