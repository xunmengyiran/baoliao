package com.baoliao.weixin.bean;

import java.util.List;

public class Product {
    private int id;

    private String openId;

    private String title;

    private String introduct;

    private String type;

    private String content;

    private String imgArr;

    private String price;

    private String expritationDate;

    private String isRefund;

    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpritationDate() {
        return expritationDate;
    }

    public void setExpritationDate(String expritationDate) {
        this.expritationDate = expritationDate;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgArr() {
        return imgArr;
    }

    public void setImgArr(String imgArr) {
        this.imgArr = imgArr;
    }
}