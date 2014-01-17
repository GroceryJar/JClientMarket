package com.jclientmarket;

import android.util.Log;
import com.jclientmarket.model.ProductsModel;

import java.util.ArrayList;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 22:54
 */

public class ProductsPresenter {
    private ArrayList<ProductsModel> products_ = new ArrayList<ProductsModel>();

    public ProductsPresenter(String list) {
        String tokens[];
        String tokens2[];
        if (list != null && list.charAt(0) == '|') {
            tokens = list.split("[|]");
            Log.e("products", tokens[0]);
            for (int i = 0 ; i != 4 ; i++) {
                tokens2 = tokens[i].split("[;]");
                if (tokens2[i] != null)
                    products_.add(new ProductsModel(Integer.parseInt(tokens2[0]), tokens2[1], tokens2[2], Integer.parseInt(tokens2[3]), Float.parseFloat(tokens2[4]), Integer.parseInt(tokens2[5])));
            }
        }

    }

}
