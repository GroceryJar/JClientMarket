package com.jclientmarket;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JClientMarket extends Activity {
    private Boolean running_;
    public SocketTCP connexion_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.connexion_ = new SocketTCP();
        this.running_ = true;
        running();
    }

    public void running() {
        Button connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View actuelView)
            {
                EditText tonEdit = (EditText)findViewById(R.id.login);
                String login = tonEdit.getText().toString();
                tonEdit = (EditText)findViewById(R.id.password);
                String pass = tonEdit.getText().toString();
                login(login, pass);
            }
        });
    }

    public void login(String login, String pass) {
        this.connexion_.send("login;"+login+";"+pass);
        String ret = this.connexion_.receive();
        if (ret.equalsIgnoreCase("Bonjour floran."));
            setContentView(R.layout.home);
    }
}


