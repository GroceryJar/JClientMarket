package com.jclientmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JClientMarket extends Activity {
    private Boolean running_;
    private Intent connexion_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.running_ = true;
        running();
    }

    public void running() {
        Button serviceBtn = (Button) findViewById(R.id.connect);
        serviceBtn.setOnClickListener( new View.OnClickListener()
        {

            @Override
            public void onClick(View actuelView)
            {
                startService(new Intent(JClientMarket.this, Connexion.class));
            }
        });
    }
}


