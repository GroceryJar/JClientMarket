package com.jclientmarket;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 15/01/2014 at 19:06
 */

public class Connexion extends IntentService {
    private Socket socket_;
    PrintWriter out_;
    BufferedReader in_;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Connexion(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        try {
            socket_ = new Socket("10.0.2.2", 4242);
            try {
                out_ = new PrintWriter(socket_.getOutputStream());
                in_ = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
