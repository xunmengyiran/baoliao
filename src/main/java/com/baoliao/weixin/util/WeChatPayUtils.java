package com.baoliao.weixin.util;

import com.baoliao.weixin.wechatpay.PayConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

import static com.baoliao.weixin.wechatpay.WXPayUtil.MD5;

public class WeChatPayUtils {
    /*public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String  getNonceStr(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();

    }

    public static String getSign(String appId,String mchId,String deviceInfo,String body,String nonceStr){
        String stringA="appid="+appId+"&body="+body+"&device_info="+deviceInfo+"&mch_id="+mchId+"&nonce_str="+nonceStr;
        //注：key为商户平台设置的密钥key
        String stringSignTemp=stringA+"&key="+ PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET ;

        //注：MD5签名方式
        String sign= null;
        try {
            sign = MD5(stringSignTemp).toUpperCase();
            //注：HMAC-SHA256签名方式
            sign=hash_hmac("sha256",stringSignTemp,key).toUpperCase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }*/

 /*   public static void main(String[] args) {
        System.out.println(getNonceStr(32));
    }*/

    public static String getIp(HttpServletRequest request) {
        //获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        return ip;
    }
}
