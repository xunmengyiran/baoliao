package com.baoliao.weixin.bean;

import java.util.Arrays;
import java.util.Date;

public class User {
    private int id;
    private String phoneNumber;
    private String openId;//用户的标识，对当前公众号唯一
    private int subscribe;//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private String nickName;//用户的昵称
    private int sex;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String city;//用户所在城市
    private String country;//用户所在国家
    private String province;//用户所在省份
    private String language;//用户的语言，简体中文为zh_CN
    private String headImgUrl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private Date subscribeTime;//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private Date cancelSubscribeTime;
    private Date bindTime;//用户绑定账号时间
    private String unionId;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
    private String remark;
    private String[] privilege;
    private String privilegeStr;

    private Long profit;

    private Long balance;

    private Float rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getCancelSubscribeTime() {
        return cancelSubscribeTime;
    }

    public void setCancelSubscribeTime(Date cancelSubscribeTime) {
        this.cancelSubscribeTime = cancelSubscribeTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getPrivilegeStr() {
        return privilegeStr;
    }

    public void setPrivilegeStr(String privilegeStr) {
        this.privilegeStr = privilegeStr;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", openId='" + openId + '\'' +
                ", subscribe=" + subscribe +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", cancelSubscribeTime=" + cancelSubscribeTime +
                ", bindTime=" + bindTime +
                ", unionId='" + unionId + '\'' +
                ", remark='" + remark + '\'' +
                ", privilege=" + Arrays.toString(privilege) +
                ", privilegeStr='" + privilegeStr + '\'' +
                ", profit=" + profit +
                ", balance=" + balance +
                ", rate=" + rate +
                '}';
    }
}