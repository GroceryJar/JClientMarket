package com.jclientmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class JClientMarket extends Activity {
    private Boolean running_;
    private Intent connexion_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.running_ = true;
        this.connexion_ = new Intent(this, Connexion.class);
        this.startService(connexion_);
        running();
    }

    public void running() {
        while (running_) {

        }
    }
}


