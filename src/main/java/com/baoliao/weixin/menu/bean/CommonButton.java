package com.baoliao.weixin.menu.bean;

/***
 * 普通按钮（子按钮）
 * @author akazam
 *
 */
public class CommonButton extends Button {
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}