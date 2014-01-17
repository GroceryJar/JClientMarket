package com.jclientmarket.presenter;

import android.app.Activity;
import android.os.Bundle;
import com.jclientmarket.R;
import com.jclientmarket.SocketTCP;
import com.jclientmarket.model.ProductsModel;

import java.util.ArrayList;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 22:54
 */

public class ProductsPresenter extends Activity {
    private ArrayList<ProductsModel> products_ = new ArrayList<ProductsModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void products() {
        SocketTCP.getInstance().send("getproducts");
        String ret = SocketTCP.getInstance().receive();
        //this.products_ = new ProductsPresenter(ret);
    }

}
