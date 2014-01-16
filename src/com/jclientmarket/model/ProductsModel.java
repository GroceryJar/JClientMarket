package com.jclientmarket.model;

/**
 * com.jclientmarket in JClientMarket
 * Made by Floran Pagliai <floran.pagliai@gmail.com>
 * Started on 16/01/2014 at 22:52
 */

public class ProductsModel {
    private int id_;
    private String designation_;
    private String description_;
    private int category_;
    private float price_;
    private int quantities_;

    public ProductsModel(int id_, String designation_, String description_, int category_, float price_, int quantities_) {
        this.id_ = id_;
        this.designation_ = designation_;
        this.description_ = description_;
        this.category_ = category_;
        this.price_ = price_;
        this.quantities_ = quantities_;
    }

}
