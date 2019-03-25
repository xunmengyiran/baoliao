package com.baoliao.weixin.wechatpay;

import com.baoliao.weixin.Constants;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {
    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = Constants.WECHAT_PARAMETER.certPath;

        File file = new File(certPath);

        InputStream certStream = new FileInputStream(file);

        this.certData = new byte[(int) file.length()];

        certStream.read(this.certData);

        certStream.close();
    }

    @Override
    String getAppID() {
        return Constants.WECHAT_PARAMETER.APPID;
    }

    @Override
    String getMchID() {
        return PayConstants.WECHAT_PAT_CONFIG.MCH_ID;
    }

    @Override
    String getKey() {
        return PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET;
    }

    @Override
    InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }
}
