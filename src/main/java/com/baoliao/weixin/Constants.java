package com.baoliao.weixin;

import com.baoliao.weixin.bean.AccessToken;

import java.text.SimpleDateFormat;

public interface Constants {
    AccessToken CURRENT_TOKEN = null;

    interface DATA_FORMAT {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmsssss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    interface WECHAT_PARAMETER {
        String APPID = "wxd69e2073c9ae0c39";
        String APPSECRET = "92cbe8e2fab977c4268c09fceb34d0d1";
        String WEIXIN_SCOPE = "snsapi_userinfo";
        String certPath = "/root/servers/file_server/jdk8/zhengshu/apiclient_cert.p12";
    }

    interface TRADE_TYPE {
        //默认交易
        int NOMAL_TRADE = 0;
        // 退款产生的交易
        int REFUND_TRADE = 1;
        /**
         * 提现产生的交易
         */
        int DEPOSIT_TRADE = 2;
    }

    interface URL {
        // 获取access_token的接口地址（GET）
        String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        // 自定义菜单创建接口（post）
        String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        // 菜单删除接口(GET)
        String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        // 菜单查询接口(GET)
        String MENU_QUERY_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
        // 网页授权后获取新token
        String OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        // 获取授权用户信息
        String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        // 主动发送模板消息
//        String TEMPLATE_MESSSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/sendSms?access_token=ACCESS_TOKEN";
        String TEMPLATE_MESSSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        // 获取ticket
        String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        // 企业支付URL(post)
        String ENTERPRISE_PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    }

    interface CONFIG {
        // 与接口配置信息中的Token要一致
        String TOKEN = "baoliao";
        // 微信跳转页面授权域名
        // dev
//        String REDIRECT_DOMAIN_NAME = "8rwumw.natappfree.cc";

    }

    /**
     * SMS模板消息
     */
    interface MSG_TEMPLATE {
        String SYNERGISTIC_MARKET_SUBSCRIBE = "感谢您的关注。";
    }
    interface MSG_TYPE {
        /**
         * 消息类型：文本
         */
        public static final String MESSAGE_TYPE_TEXT = "text";

        /**
         * 消息类型：音乐
         */
        public static final String MESSAGE_TYPE_MUSIC = "music";

        /**
         * 消息类型：图文
         */
        public static final String MESSAGE_TYPE_NEWS = "news";

        /**
         * 消息类型：图片
         */
        public static final String MESSAGE_TYPE_IMAGE = "image";

        /**
         * 消息类型：链接
         */
        public static final String MESSAGE_TYPE_LINK = "link";

        /**
         * 消息类型：地理位置
         */
        public static final String MESSAGE_TYPE_LOCATION = "location";

        /**
         * 消息类型：音频
         */
        public static final String MESSAGE_TYPE_VOICE = "voice";

        /**
         * 消息类型：推送
         */
        public static final String MESSAGE_TYPE_EVENT = "event";

        /**
         * 事件类型：subscribe(订阅)
         */
        public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

        /**
         * 事件类型：unsubscribe(取消订阅)
         */
        public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

        /**
         * 事件类型：CLICK(自定义菜单点击事件)
         */
        public static final String EVENT_TYPE_CLICK = "CLICK";

        public static final String REQ_TIMED_TASK_ = "task";
    }
}
