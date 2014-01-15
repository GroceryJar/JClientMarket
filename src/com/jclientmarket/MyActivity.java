package com.jclientmarket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class MyActivity extends Activity implements Runnable {
    private Thread t_;
    private Socket socket_;
    PrintWriter out_;
    BufferedReader in_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        t_ = new Thread(this);
        t_.start();
    }

    public void run() {
        try {
            socket_ = new Socket("10.0.2.2", 4242);
            Button myButton = (Button)findViewById(R.id.connect);
            try {
                out_ = new PrintWriter(socket_.getOutputStream());
                in_ = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
                myButton.setOnClickListener(clickListener);
                while (true) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText tonEdit = (EditText)findViewById(R.id.login);
            String login = tonEdit.getText().toString();
            tonEdit = (EditText)findViewById(R.id.password);
            String pass = tonEdit.getText().toString();
            out_.write("login;" + login + ";" + pass + "\n");
            out_.flush();
            setContentView(R.layout.home);

        }
    };
}
