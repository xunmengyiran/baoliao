package com.baoliao.weixin.util;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.MyX509TrustManager;
import com.baoliao.weixin.bean.AccessToken;
import com.baoliao.weixin.menu.bean.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Calendar;

public class WeixinIntefaceUtil {

    protected static final Log log = LogFactory.getLog(WeixinIntefaceUtil.class);

    /**
     * 获取accessToken
     *
     * @param appid
     * @param appsecret
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        log.info("接收到的appid是:" + appid + ",接收到的appsecret是" + appsecret);
        AccessToken accessToken = null;
        Long lNow = Calendar.getInstance().getTimeInMillis() - 50;

        boolean isAgain = false;
        if (Constants.CURRENT_TOKEN == null) {
            isAgain = true;
        } else {
            if (Constants.CURRENT_TOKEN.getExpiredTime() <= lNow) {
                isAgain = true;
            }
        }

        if (isAgain) {
            String requestUrl = Constants.URL.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    accessToken = new AccessToken();
                    accessToken.setToken(jsonObject.getString("access_token"));
                    accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    accessToken.setExpiredTime(Calendar.getInstance().getTimeInMillis() + (accessToken.getExpiresIn()));
                } catch (JSONException e) {
                    accessToken = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{} errcode:" + jsonObject.getInt("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
                }
            }
        }
        return accessToken;
    }

    /**
     * 模拟http请求
     *
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setRequestProperty("Accept-Charset", "utf-8");
            httpUrlConn.setRequestProperty("contentType", "UTF-8");
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            System.out.println("outputStr:" + outputStr);
            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
            log.info("weixin res:" + buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }


    public static Long toLong(Object obj) {
        Long result = 0L;
        try {
            if (obj != null)
                result = Long.parseLong(obj.toString());
        } catch (Exception e) {
            result = 0L;
        }
        return result;
    }

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = Constants.URL.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败  errcode:" + jsonObject.getInt("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
                System.out.println("创建菜单失败  errcode:" + jsonObject.getInt("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
