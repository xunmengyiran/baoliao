package com.baoliao.weixin.bean;

import java.util.Date;

public class FocusInfo {

    private int id;
    private String selfOpenId;
    private String otherOpenId;
    private Date createTime;
    private int isCancel;
    private Date cancelTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelfOpenId() {
        return selfOpenId;
    }

    public void setSelfOpenId(String selfOpenId) {
        this.selfOpenId = selfOpenId;
    }

    public String getOtherOpenId() {
        return otherOpenId;
    }

    public void setOtherOpenId(String otherOpenId) {
        this.otherOpenId = otherOpenId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        return "FocusInfo{" +
                "id=" + id +
                ", selfOpenId='" + selfOpenId + '\'' +
                ", otherOpenId='" + otherOpenId + '\'' +
                ", createTime=" + createTime +
                ", isCancel=" + isCancel +
                ", cancelTime=" + cancelTime +
                '}';
    }
}
