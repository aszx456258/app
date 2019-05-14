package com.example.lee.app;

import java.io.Serializable;

public class GoodsEntity implements Serializable {
    public String goodsName;//货物名称
    public String goodsPrice;//货物价格
    public int position;
    public int index;

    public GoodsEntity() {
    }

    public GoodsEntity(String goodsName, String goodsPrice,int position,int index) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.position = position;
        this.index = index;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public void setIndex(int index){this.index=index;}
    public String getGoodsPrice() {
        return goodsPrice;
    }
    public int getPosition() {
        return position;
    }
    public int getIndex() {
        return index;
    }
    public void setPosition(int position) {
        this.position = position;
    }


    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                '}';
    }
}
