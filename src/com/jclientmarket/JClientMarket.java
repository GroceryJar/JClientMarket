package com.jclientmarket;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JClientMarket extends Activity {
    public SocketTCP connexion_;
    private ProductsPresenter products_ = new ProductsPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.connexion_ = new SocketTCP();
        running();
        products();
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
        Button registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View actuelView)
            {
                EditText tonEdit = (EditText)findViewById(R.id.login);
                String login = tonEdit.getText().toString();
                tonEdit = (EditText)findViewById(R.id.password);
                String pass = tonEdit.getText().toString();
                register(login, pass);
            }
        });
    }

    public void login(String login, String pass) {
        String message;
        this.connexion_.send("login;"+login+";"+pass);
        String ret = this.connexion_.receive();
        if (ret != null && (ret.equalsIgnoreCase("loginOk") || ret.equalsIgnoreCase("loginLogged"))) {
            setContentView(R.layout.home);
            if (ret.equalsIgnoreCase("loginOk"))
                message =  "Bonjour " + login + ".";
            else
                message = "Vous êtes déjà connecté.";
        } else if (ret != null && ret.equalsIgnoreCase("loginError"))
            message = "Utilisateur inconnu ou mot de passe erroné.";
        else
            message = "Utilisateur déjà connecté.";
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 75);
        toast.show();
    }

    public void register(String login, String pass) {
        String message;
        if (!login.equalsIgnoreCase("Login")) {
            this.connexion_.send("register;"+login+";"+pass);
            String ret = this.connexion_.receive();
            if (ret != null && ret.equalsIgnoreCase("registerOk"))
                 message = "Nouvel utilisateur enregistré.\n Vous pouvez maintenant vous connecter.";
            else if (ret != null && ret.equalsIgnoreCase("registerError"))
                message = "Un utilisateur utilise déjà ce pseudo.";
            else
                message = "Vous êtes déjà connecté.";
        } else {
            message = "Veuillez entrer un pseudo valide.";
        }
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 75);
        toast.show();
    }

    public void products() {
        String message;
        this.connexion_.send("getproducts");
        String ret = this.connexion_.receive();
        Toast toast = Toast.makeText(getApplicationContext(), ret, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 75);
        toast.show();
    }
}


