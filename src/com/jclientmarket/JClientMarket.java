package com.jclientmarket;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JClientMarket extends Activity {
    public SocketTCP connexion_;
    private ProductsPresenter products_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.connexion_ = new SocketTCP();
        running();
    }

    public void running() {
        Button connectBtn = (Button)findViewById(R.id.connect);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                EditText tonEdit = (EditText)findViewById(R.id.login);
                String login = tonEdit.getText().toString();
                tonEdit = (EditText)findViewById(R.id.password);
                String pass = tonEdit.getText().toString();
                login(login, pass);


            }
        });
        Button registerBtn = (Button)findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                EditText tonEdit = (EditText)findViewById(R.id.login);
                String login = tonEdit.getText().toString();
                tonEdit = (EditText)findViewById(R.id.password);
                String pass = tonEdit.getText().toString();
                register(login, pass);
            }
        });
        Button testBtn = (Button)findViewById(R.id.test);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                products();
            }
        });
    }

    public void login(String login, String pass) {
        String message;
        String ret = null;
        this.connexion_.send("login;" + login + ";" + pass);
        ret = this.connexion_.receive();
        if (ret.equalsIgnoreCase("loginOk")) {
            setContentView(R.layout.home);
            message = "Bonjour " + login + ".";
        } else if (ret.equalsIgnoreCase("loginLogged")) {
            setContentView(R.layout.home);
            message = "Vous êtes déjà connecté.";
        } else if (ret.equalsIgnoreCase("loginError"))
            message = "Utilisateur inconnu ou mot de passe erroné.";
        else
            message = "Erreur.";
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 75);
        toast.show();
    }

    public void register(String login, String pass) {
        String message;
        String ret;
        if (!login.equalsIgnoreCase("Login")) {
            this.connexion_.send("register;" + login + ";" + pass);
            ret = this.connexion_.receive();
            if (ret.equalsIgnoreCase("registerOk"))
                message = "Nouvel utilisateur enregistré.\n Vous pouvez maintenant vous connecter.";
            else if (ret.equalsIgnoreCase("registerError"))
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
        this.connexion_.send("getproducts");
        String ret = this.connexion_.receive();
        this.products_ = new ProductsPresenter(ret);
    }
}


