package com.baoliao.weixin.bean;

import java.util.Date;

public class Trade {
    private int id;

    private String buyerOpenId;

    private String sellerOpenId;

    private String payType;

    private int productId;

    private Date createTime;

    private String money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(String buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
    }

    public String getSellerOpenId() {
        return sellerOpenId;
    }

    public void setSellerOpenId(String sellerOpenId) {
        this.sellerOpenId = sellerOpenId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", buyerOpenId='" + buyerOpenId + '\'' +
                ", sellerOpenId='" + sellerOpenId + '\'' +
                ", payType='" + payType + '\'' +
                ", productId=" + productId +
                ", createTime=" + createTime +
                ", money='" + money + '\'' +
                '}';
    }
}