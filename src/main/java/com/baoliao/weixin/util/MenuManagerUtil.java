package com.baoliao.weixin.util;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.AccessToken;
import com.baoliao.weixin.menu.bean.Button;
import com.baoliao.weixin.menu.bean.ComplexButton;
import com.baoliao.weixin.menu.bean.Menu;
import com.baoliao.weixin.menu.bean.ViewButton;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MenuManagerUtil {

    protected static final Log log = LogFactory.getLog(MenuManagerUtil.class);

    public static void createMenu(String appid,String  appsecret,String redirect_domain_name) {
        AccessToken at = WeixinIntefaceUtil.getAccessToken(appid, appsecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinIntefaceUtil.createMenu(getMenu(appid,redirect_domain_name), at.getToken());
            System.out.println("TOKEN : " + at.getToken());
            // 判断菜单创建结果
            if (0 == result) {
                log.info("菜单创建成功！");
            }else {
                log.info("菜单创建失败，错误码：" + result);

                //调用接口删除菜单
                String requestUrl = Constants.URL.MENU_DELETE_URL.replace("ACCESS_TOKEN", at.getToken());
                JSONObject jobj = WeixinIntefaceUtil.httpRequest(requestUrl, "GET", "");
                log.info(jobj.toString());
            }
        }
            // 删除
//        String requestUrl = Constants.URL.MENU_DELETE_URL.replace("ACCESS_TOKEN", at.getToken());
//        JSONObject jobj =WeixinIntefaceUtil.httpRequest(requestUrl, "GET", "");
//        log.info(jobj.toString());
//         查询
//        String requestUrl = Constants.URL.MENU_QUERY_URL.replace("ACCESS_TOKEN", at.getToken());
//        JSONObject jobj =WeixinIntefaceUtil.httpRequest(requestUrl, "GET", "");
//        log.info(jobj.toString());
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu(String appid, String redirect_domain_name) {
        // 菜单1
        ComplexButton button1 = new ComplexButton();
        button1.setName("彩料助手");
        button1.setType("view");
        button1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/user/queryUserList&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//        // 菜单1的子菜单
//        ViewButton button1_1 = new ViewButton();
//        button1_1.setName("订购时长卡");
//        button1_1.setType("view");
//        button1_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/redirect_time_card.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button1_2 = new ViewButton();
//        button1_2.setName("订购出境WiFi");
//        button1_2.setType("view");
//        button1_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/buy_guoji.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button1_3 = new ViewButton();
//        button1_3.setName("充值中心");
//        button1_3.setType("view");
//        button1_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/chongzhi.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button1_4 = new ViewButton();
//        button1_4.setName("APP下载");
//        button1_4.setType("view");
//        button1_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/app_dow.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button1_5 = new ViewButton();
//        button1_5.setName("账户绑定");
//        button1_5.setType("view");
//        button1_5.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/account_binding.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        button1.setSub_button(new Button[]{button1_1,button1_2,button1_3,button1_4,button1_5});
//
        // 菜单2
        ComplexButton button2 = new ComplexButton();
        button2.setName("我的");
        button2.setType("view");
        button2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/user/queryUserList&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//        // 菜单2的子菜单
////        ViewButton button2_1 = new ViewButton();
////        button2_1.setName("优惠领取");
////        button2_1.setType("view");
////        button2_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/account_binding.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button2_2 = new ViewButton();
//        button2_2.setName("有奖调查");
//        button2_2.setType("view");
//        button2_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/youjiang.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button2_3 = new ViewButton();
//        button2_3.setName("最in服务");
//        button2_3.setType("view");
//        button2_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/in.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button2_4 = new ViewButton();
//        button2_4.setName("信息安全公告");
//        button2_4.setType("view");
//        button2_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/info_safe.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        button2.setSub_button(new Button[]{button2_2,button2_3,button2_4});
//
//        // 菜单3
//        ComplexButton button3 = new ComplexButton();
//        button3.setName("最in服务");
//
//
//        ViewButton button3_1 = new ViewButton();
//        button3_1.setName("联系我们");
//        button3_1.setType("click");
////        button3_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/weixin/safety_manager.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//        button3_1.setKey("contact");
//
//        ViewButton button3_2 = new ViewButton();
//        button3_2.setName("在线客服");
//        button3_2.setType("click");
////        button2_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/weixin/safety_manager.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//        button3_2.setKey("onLine");
//
//        ViewButton button3_3 = new ViewButton();
//        button3_3.setName("设备流量查询");
//        button3_3.setType("view");
//        button3_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/product/residualInfoSearch.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button3_4 = new ViewButton();
//        button3_4.setName("年付套餐自主管理");
//        button3_4.setType("view");
//        button3_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/view/weixin/year_pay.jsp&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        ViewButton button3_5 = new ViewButton();
//        button3_5.setName("我的订单(待付/取消)");
//        button3_5.setType("view");
//        button3_5.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://"+redirect_domain_name+"/queryMyOrder.do&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
//
//        button3.setSub_button(new Button[]{button3_1,button3_2,button3_3,button3_4,button3_5});
//
        Menu menu = new Menu();
        menu.setButton(new Button[]{button1, button2});
        return menu;
    }
}