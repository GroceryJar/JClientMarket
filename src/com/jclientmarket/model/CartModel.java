package com.jclientmarket.model;

/**
 * com.jclientmarket.model in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 17/01/2014 at 23:02
 */

public class CartModel {
    private int id_;
    private int productId_;
    private int quantity_;
    private String designation_;
    private float price_;

    public CartModel(int id_, int productId_, int quantity_, String designation_, float price_) {
        this.id_ = id_;
        this.productId_ = productId_;
        this.quantity_ = quantity_;
        this.designation_ = designation_;
        this.price_ = price_;
    }

    public int getId_() {
        return id_;
    }

    public int getProductId_() {
        return productId_;
    }

    public int getQuantity_() {
        return quantity_;
    }

    public float getPrice_() {
        return price_;
    }

    public String getDesignation_() {
        return designation_;
    }
}
