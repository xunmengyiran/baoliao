package com.baoliao.weixin.bean;

import java.util.Date;

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

    private String qrImgName;

    private Date createTime;

    private int isDelete;

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

    public String getQrImgName() {
        return qrImgName;
    }

    public void setQrImgName(String qrImgName) {
        this.qrImgName = qrImgName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", title='" + title + '\'' +
                ", introduct='" + introduct + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", imgArr='" + imgArr + '\'' +
                ", price='" + price + '\'' +
                ", expritationDate='" + expritationDate + '\'' +
                ", isRefund='" + isRefund + '\'' +
                ", code='" + code + '\'' +
                ", qrImgName='" + qrImgName + '\'' +
                ", createTime=" + createTime +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}