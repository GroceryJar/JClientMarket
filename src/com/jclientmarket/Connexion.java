package com.jclientmarket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 15/01/2014 at 19:06
 */

public class Connexion extends Service implements Runnable {
    private Socket socket_;
    Thread t_;
    PrintWriter out_;
    BufferedReader in_;

    @Override
    public void onCreate() {
        t_ = new Thread(this);
        t_.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public void run() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void send(String message) {
        out_.write("message"+"\n");
        out_.flush();
    }

}
