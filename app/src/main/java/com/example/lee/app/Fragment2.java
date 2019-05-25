package com.example.lee.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lee.hi.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Carson_Ho on 16/5/23.
 */
public class Fragment2 extends Fragment
{
    private EditText edtname,edttext;
    private TextView textview1;
    private Button button1;
    String tmp;                // 暫存文字訊息
    Socket clientSocket;

    public static Handler mHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item2, null);
        edtname = (EditText)view.findViewById(R.id.edtname);
        edttext = (EditText)view.findViewById(R.id.edttext);
        button1 = (Button)view.findViewById(R.id.button1);
        textview1 = (TextView)view.findViewById(R.id.textView1);
        button1.setOnClickListener(btnlistener);
        Thread t = new Thread(readData);
        // 啟動執行緒
        t.start();
        return view;
    }
    private View.OnClickListener btnlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (clientSocket.isConnected()) {
//                BufferedWriter bw;
                new Thread(new Runnable(){
                    PrintWriter pout = null;
                    @Override
                    public void run() {
                        try {
                            // 取得網路輸出串流
//                    bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//
//                    // 寫入訊息
//                    bw.write(edtname.getText() + ":" + edttext.getText() + "\n");
//
//                    // 立即發送
//                    bw.flush();
                            String msg = edtname.getText() + ":" + edttext.getText();
                            pout = new PrintWriter(
                                    new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8")), true);
                            pout.println(msg);

                        } catch (IOException e) {

                        }
                    }
                }).start();
//                try {
//                    // 取得網路輸出串流
////                    bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
////
////                    // 寫入訊息
////                    bw.write(edtname.getText() + ":" + edttext.getText() + "\n");
////
////                    // 立即發送
////                    bw.flush();
//                        String msg = edtname.getText() + ":" + edttext.getText();
//                        pout = new PrintWriter(
//                                new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8")), true);
//                        pout.println(msg);
//
//                } catch (IOException e) {
//
//                }
                // 將文字方塊清空
                edttext.setText("");
            }
        }
    };
    private Runnable updateText = new Runnable() {
        public void run() {
            // 加入新訊息並換行
            textview1.append(tmp + "\n");
        }
    };
    private Runnable readData = new Runnable() {
        public void run() {
            // server端的IP
            InetAddress serverIp;
            try {
                // 以內定(本機電腦端)IP為Server端
                serverIp = InetAddress.getByName("192.168.1.102");
                int serverPort = 5050;
                clientSocket = new Socket(serverIp, serverPort);

                // 取得網路輸入串流
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));

                // 當連線後
                while (clientSocket.isConnected()) {
                    // 取得網路訊息
                    tmp = br.readLine();

                    // 如果不是空訊息則
                    if(tmp!=null)
                        // 顯示新的訊息
                        mHandler.post(updateText);
                }

            } catch (IOException e) {

            }
        }
    };

}