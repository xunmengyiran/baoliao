package com.baoliao.weixin;

import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.util.MessageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WeiXinService {
    protected static final Log log = LogFactory.getLog(WeiXinService.class);

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request, UserDao userDao) {
        String result = "";
        try {
            // 解析微信服务器发来的消息（xml）
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 构建消息xml回传给微信服务器
            result = MessageUtil.buildXml(requestMap,userDao);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
