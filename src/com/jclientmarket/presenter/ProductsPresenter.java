package com.jclientmarket.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.jclientmarket.JClientMarket;
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

        Button logoutBtn = (Button)findViewById(R.id.test);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                SocketTCP.getInstance().send("logout");
                Intent intent = new Intent(ProductsPresenter.this, JClientMarket.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void products() {
        SocketTCP.getInstance().send("getproducts");
        String ret = SocketTCP.getInstance().receive();
        //this.products_ = new ProductsPresenter(ret);
    }

}
