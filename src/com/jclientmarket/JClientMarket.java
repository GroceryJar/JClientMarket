package com.jclientmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jclientmarket.presenter.ProductsPresenter;

public class JClientMarket extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocketTCP.getInstance();
        setContentView(R.layout.login);

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
    }

    public void login(String login, String pass) {
        String message;
        String ret;
        SocketTCP.getInstance().send("login;" + login + ";" + pass);
        ret = SocketTCP.getInstance().receive();
        if (ret.equalsIgnoreCase("loginOk")) {
            message = "Bonjour " + login + ".";
            Intent intent = new Intent(JClientMarket.this, ProductsPresenter.class);
            startActivity(intent);
            finish();
        } else if (ret.equalsIgnoreCase("loginLogged")) {
            message = "Vous êtes déjà connecté.";
            Intent intent = new Intent(JClientMarket.this, ProductsPresenter.class);
            startActivity(intent);
            finish();
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
            SocketTCP.getInstance().send("register;" + login + ";" + pass);
            ret = SocketTCP.getInstance().receive();
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
}


