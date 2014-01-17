package com.jclientmarket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.jclientmarket.model.ProductsModel;

import java.util.ArrayList;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 22:54
 */

public class ProductsPresenter extends Activity{
    public SocketTCP connexion_;
    private ArrayList<ProductsModel> products_ = new ArrayList<ProductsModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        this.connexion_.getInstance();
    }

    public void products() {
        this.connexion_.send("getproducts");
        String ret = this.connexion_.receive();
        //this.products_ = new ProductsPresenter(ret);
    }

}
