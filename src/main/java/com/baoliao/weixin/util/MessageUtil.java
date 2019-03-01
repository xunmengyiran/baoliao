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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;


@Service
public class MessageUtil {
    protected static final Log log = LogFactory.getLog(MessageUtil.class);

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

    public static String buildXml(Map<String, String> map, UserDao userDao) {
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
            if (map.get("EventKey").equals("onLine")) {
                // 按钮的点击事件
                /*BaseMessage bsg = new BaseMessage();
                bsg.setToUserName(fromUserName);
                bsg.setFromUserName(toUserName);
                bsg.setCreateTime(new Date().getTime());
                bsg.setMsgType("transfer_customer_service");
                SignUtil.xstream.alias("xml", bsg.getClass());
                return SignUtil.xstream.toXML(bsg);*/
                String content = "请求处理异常，请稍候尝试！";
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(Constants.MSG_TYPE.MESSAGE_TYPE_TEXT);
                textMessage.setFuncFlag(0);
                content = "请输入您需要咨询的问题，马上会有客服人员回复！";
                textMessage.setContent(content);
                SignUtil.xstream.alias("xml", textMessage.getClass());
                return SignUtil.xstream.toXML(textMessage);
            } else if (map.get("EventKey").equals("contact")) {
                String content = "请求处理异常，请稍候尝试！";
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(Constants.MSG_TYPE.MESSAGE_TYPE_TEXT);
                textMessage.setFuncFlag(0);
                content = "电话服务热线：4008210539\n" +
                        "QQ服务群：272068846、421109495\n" +
                        "邮箱：wifi@support.akazam.com";
                textMessage.setContent(content);
                SignUtil.xstream.alias("xml", textMessage.getClass());
                return SignUtil.xstream.toXML(textMessage);
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
