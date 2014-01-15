package com.jclientmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 15/01/2014 at 19:05
 */

public class Login extends Activity {
    private PrintWriter out_;
    private BufferedReader in_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            Button myButton = (Button)findViewById(R.id.connect);
            myButton.setOnClickListener(clickListener);
            EditText tonEdit = (EditText)findViewById(R.id.login);
            String login = tonEdit.getText().toString();
            tonEdit = (EditText)findViewById(R.id.password);
            String pass = tonEdit.getText().toString();
            out_.write("login;" + login + ";" + pass + "\n");
            out_.flush();
        }
    };
}
