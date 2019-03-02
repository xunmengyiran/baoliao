package com.baoliao.weixin.util;

import com.akazam.wifi.guoman.weixin.message.resp.BaseMessage;
import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.AccessToken;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.message.resp.TextMessage;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class MessageUtil {
    protected static final Log log = LogFactory.getLog(MessageUtil.class);

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        return map;
    }

    public static String buildXml(Map<String, String> map, UserDao userDao,HttpServletRequest req,HttpServletResponse res) {
        request = req;
        response = res;
        String result = "";
        // 发送方帐号（open_id）
        String fromUserName = map.get("FromUserName");
        // 公众帐号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");

        // 如果请求是文本类型的消息
        if (Constants.MSG_TYPE.MESSAGE_TYPE_TEXT.equals(msgType)) {
            result = buildTextMessage(map, fromUserName, toUserName, userDao);
        }
        // 如果请求是图片类型的消息
        else if (Constants.MSG_TYPE.MESSAGE_TYPE_IMAGE.equals(msgType)) {
            result = new MessageUtil().buildImageMessage(map, fromUserName, toUserName);
        }
        // 如果请求是事件类型的消息
        else if (Constants.MSG_TYPE.MESSAGE_TYPE_EVENT.equals(msgType)) {
            // 事件类型
            String eventType = map.get("Event");
            result = new MessageUtil().buildEventMessage(map, fromUserName, toUserName, eventType, userDao);
        }
        return result;
    }

    public static String buildTextMessage(Map<String, String> map, String fromUserName, String toUserName, UserDao userDao) {
    return null;
    }

    public String buildImageMessage(Map<String, String> map, String fromUserName, String toUserName) {
        return "";
    }

    public String buildEventMessage(Map<String, String> map, String fromUserName, String toUserName, String eventType, UserDao userDao) {
        // 关注
        if (eventType.equals(Constants.MSG_TYPE.EVENT_TYPE_SUBSCRIBE)) {
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(Constants.MSG_TYPE.MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            textMessage.setContent(Constants.MSG_TEMPLATE.SYNERGISTIC_MARKET_SUBSCRIBE);
            SignUtil.xstream.alias("xml", textMessage.getClass());
            try {
                userDao.insertFollowInfo(fromUserName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return SignUtil.xstream.toXML(textMessage);
        } else if (eventType.equals(Constants.MSG_TYPE.EVENT_TYPE_UNSUBSCRIBE)) {
            // 取消关注，将关注字段从1->0
            try {
                userDao.updateFollowInfo(fromUserName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (eventType.equals(Constants.MSG_TYPE.EVENT_TYPE_CLICK)) {
            if (map.get("EventKey").equals("1111")) {
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (map.get("EventKey").equals("contact")) {
            }
        }
        return "";
    }

    /**
     * 发送模板消息
     *
     * @param message
     * @return
     */
    public static int sendCustomerService(String message) {
        int result = 0;
        AccessToken at = WeixinIntefaceUtil.getAccessToken(Constants.WECHAT_PARAMETER.APPID, Constants.WECHAT_PARAMETER.APPSECRET);
        String url = Constants.URL.TEMPLATE_MESSSAGE_SEND_URL.replace("ACCESS_TOKEN", at.getToken());
        JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(url, "POST", message);
        if (null != jsonObject) {
            result = jsonObject.optInt("errcode");
            log.info("发送模板消息信息" + result + ":" + jsonObject.toString());
        }
        return result;
    }
}
