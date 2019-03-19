package com.baoliao.weixin.bean;

import java.util.Date;

public class Trade {
    private int id;

    private String buyerOpenId;

    private String sellerOpenId;

    private int payType;

    private int tradeType;

    private int productId;

    private String productTitle;

    private Date createTime;

    private String money;

    private String sellerCount;

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

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getSellerCount() {
        return sellerCount;
    }

    public void setSellerCount(String sellerCount) {
        this.sellerCount = sellerCount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", buyerOpenId='" + buyerOpenId + '\'' +
                ", sellerOpenId='" + sellerOpenId + '\'' +
                ", payType=" + payType +
                ", tradeType=" + tradeType +
                ", productId=" + productId +
                ", productTitle='" + productTitle + '\'' +
                ", createTime=" + createTime +
                ", money='" + money + '\'' +
                ", sellerCount='" + sellerCount + '\'' +
                '}';
    }
}