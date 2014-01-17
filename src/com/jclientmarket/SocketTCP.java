package com.jclientmarket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 15:06
 */

public final class SocketTCP implements Runnable {
    private static volatile SocketTCP instance = null;
    private Thread t_;
    private Socket socket_;
    private PrintWriter out_;
    private BufferedReader in_;
    private Boolean connected_;
    private String message_;

    private SocketTCP() {
        super();
        this.connected_ = false;
        this.message_ = null;
        this.t_ = new Thread(this);
        this.t_.start();
    }

    public final static SocketTCP getInstance(){
        if (SocketTCP.instance == null) {
            synchronized(SocketTCP.class) {
                if (SocketTCP.instance == null) {
                    SocketTCP.instance = new SocketTCP();
                }
            }
        }
        return SocketTCP.instance;
    }

    public void run() {
        String message = "";
        if (!connected_) {
            try {
                this.socket_ = new Socket("10.0.2.2", 4242);
                this.connected_ = true;
                try {
                    this.out_ = new PrintWriter(socket_.getOutputStream());
                    this.in_ = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            char charCur[] = new char[1];
            while (in_.read(charCur, 0, 1) != -1) {
                if (charCur[0] != '\u0000' && charCur[0] != '\n' && charCur[0] != '\r')
                    message += charCur[0];
                else if (!message.equalsIgnoreCase("")) {
                    this.message_ = message;
                    message = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        this.out_.write(message+"\n");
        this.out_.flush();
    }

    public String receive() {
        while (message_ == null) {}
        String message = message_;
        message_ = null;
        return message;
    }
}
