package com.jclientmarket.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.jclientmarket.JClientMarket;
import com.jclientmarket.R;
import com.jclientmarket.SocketTCP;
import com.jclientmarket.model.ProductsModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 22:54
 */

public class ProductsPresenter extends Activity {
    private ArrayList<ProductsModel> products_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        products();

        Button productPageBtn = (Button)findViewById(R.id.productPage);
        productPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                products();
            }
        });

        Button cartPageBtn = (Button)findViewById(R.id.cartPage);
        cartPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View actuelView) {
                Intent intent = new Intent(ProductsPresenter.this, CartPresenter.class);
                startActivity(intent);
                finish();
            }
        });
        Button logoutBtn = (Button)findViewById(R.id.logout);
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
        products_ = new ArrayList<ProductsModel>();

        SocketTCP.getInstance().send("getproducts");
        String ret = SocketTCP.getInstance().receive();
        String token[] = ret.split("[|]");
        for (int i = 1 ; i != token.length ; i++) {
            String token2[] = token[i].split("[;]");
            this.products_.add(new ProductsModel(Integer.parseInt(token2[0]), token2[1], token2[2], Integer.parseInt(token2[3]), Float.parseFloat(token2[4]), Integer.parseInt(token2[5])));
        }
        update();
    }

    public void update() {
        final ListView maListViewPerso = (ListView)findViewById(R.id.productsList);
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        for (int i = 0 ; i != this.products_.size() ; i++) {
            if (this.products_.get(i).getQuantities_() > 0) {
                map = new HashMap<String, String>();
                map.put("id", String.valueOf(this.products_.get(i).getId_()));
                map.put("designation", this.products_.get(i).getDesignation_());
                map.put("description", this.products_.get(i).getDescription_());
                map.put("price", String.valueOf(this.products_.get(i).getPrice_() + " €  " + String.valueOf(this.products_.get(i).getQuantities_())));
                map.put("img", String.valueOf(R.drawable.add_panier));
                listItem.add(map);
            }
        }

        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.affichageitem,
                new String[]{"img", "designation", "description", "price"}, new int[]{R.id.img, R.id.titre, R.id.description, R.id.price});
        maListViewPerso.setAdapter(mSchedule);
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                final HashMap<String, String> map = (HashMap<String, String>)maListViewPerso.getItemAtPosition(position);
                AlertDialog.Builder adb = new AlertDialog.Builder(ProductsPresenter.this, AlertDialog.THEME_HOLO_LIGHT);
                adb.setTitle(getString(R.string.addToCart));
                adb.setMessage(map.get("designation") + " à " + getString(R.string.disp) + map.get("price"));
                adb.setNegativeButton(getString(R.string.cancel), null);
                adb.setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SocketTCP.getInstance().send("addtocart;" + map.get("id"));
                        if (products_.get(position).getQuantities_() > 1)
                            products_.get(position).setQuantities_(products_.get(position).getQuantities_() - 1);
                        else
                            products_.remove(position);
                        update();
                        String ret = SocketTCP.getInstance().receive();
                        String message = "";
                        if (ret.equalsIgnoreCase("addtocartOk"))
                            message = getString(R.string.addToCartOk);
                        else if (ret.equalsIgnoreCase("addtocartError"))
                            message = getString(R.string.addToCartError);
                        else
                            message = getString(R.string.error);
                        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 75);
                        toast.show();
                    }
                });
                adb.show();
            }
        });

    }
}
