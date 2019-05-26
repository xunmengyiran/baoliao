package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.Trade;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.dao.ProductDao;
import com.baoliao.weixin.dao.TradeDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.TradeService;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
import com.baoliao.weixin.util.WeChatPayUtils;
import com.baoliao.weixin.wechatpay.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    private Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    TradeDao tradeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Value("${domain_name}")
    private String domainName;

    @Override
    public String payByBalance(HttpServletRequest request) throws Exception {
        String buyerOpenId = request.getParameter("buyerOpenId");
        String sellerOpenId = request.getParameter("sellerOpenId");
        String amount = request.getParameter("amount");
        String payType = request.getParameter("type");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        Date currDate = new Date();

        Trade trade = new Trade();
        trade.setProductId(Integer.parseInt(id));
        trade.setMoney(amount);
        trade.setCreateTime(currDate);
        trade.setBuyerOpenId(buyerOpenId);
        trade.setSellerOpenId(sellerOpenId);
        trade.setPayType(Integer.parseInt(payType));
        log.info("========trade=============" + trade.toString());
        JSONObject jObject = new JSONObject();
        try {
            int num = tradeDao.saveTradeInfo(trade);
            if (num == 1) {
                resultMap.put("success", true);
                jObject = JSONObject.fromObject(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存交易信息异常！" + e);
        }
        return jObject.toString();
    }

    @Override
    public String payByWeixin(HttpServletRequest request) throws Exception {
        String buyerOpenId = request.getParameter("buyerOpenId");
        String sellerOpenId = request.getParameter("sellerOpenId");
        String amount = request.getParameter("amount");
        String payType = request.getParameter("type");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        Date currDate = new Date();
        ServletContext application = request.getSession().getServletContext();
        application.setAttribute(id, buyerOpenId);
        application.setAttribute("tradeType",0);
        Map<String, String> data = new HashMap<String, String>();
        String nonceStr = WXPayUtil.generateNonceStr();
        String appId = Constants.WECHAT_PARAMETER.APPID;
        String mchId = PayConstants.WECHAT_PAT_CONFIG.MCH_ID;
        String deviceInfo = "WEB";

//        data.put("appid", appId);
//        data.put("mch_id",mchId );
        data.put("device_info", deviceInfo);
//        data.put("nonce_str", nonceStr);
//        data.put("sign", "");
//        data.put("sign_type", WXPayConstants.MD5);
        data.put("body", "信息服务费");
        data.put("detail", "信息服务费");
        data.put("attach", "必赢大数据");
        data.put("out_trade_no", id);
        data.put("total_fee", String.valueOf((int) (Double.parseDouble(amount) * 100)));
        data.put("spbill_create_ip", WeChatPayUtils.getIp(request));
//        data.put("time_start", Constants.DATA_FORMAT.sdf2.format(currDate));
        data.put("notify_url", domainName + "/trade/paysuccessreturn");
        data.put("trade_type", "JSAPI");
        data.put("openid", buyerOpenId);

        log.info("请求统一下单接口参数:" + data);
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> unifiedOrderMap = wxpay.unifiedOrder(data);

        log.info("调用统一下单接口返回结果:" + unifiedOrderMap);

        String returnCode = unifiedOrderMap.get("return_code");
        String resultCode = unifiedOrderMap.get("result_code");
        try {
            if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                log.info("微信支付下单成功");
                // 进行签名校验
                Map<String, String> map = new HashMap<>();
                map.put("return_code", unifiedOrderMap.get("return_code"));
                map.put("return_msg", unifiedOrderMap.get("return_msg"));
                map.put("appid", unifiedOrderMap.get("appid"));
                map.put("mch_id", unifiedOrderMap.get("mch_id"));
                map.put("nonce_str", unifiedOrderMap.get("nonce_str"));
                map.put("result_code", unifiedOrderMap.get("result_code"));
                map.put("prepay_id", unifiedOrderMap.get("prepay_id"));
                map.put("trade_type", unifiedOrderMap.get("trade_type"));
                // 生成签名
                String mySign = WXPayUtil.generateSignature(map, PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET);
                // 微信返回的签名
                String wxSign = unifiedOrderMap.get("sign");
                log.info("返回的签名" + wxSign);
                log.info("最后生成的签名" + mySign);
                System.out.println("最后生成的签名的参数:" + map);
                // 需要返回给页面的数据
                Map<String, String> returnMap = new HashMap<>();
                // TODO 验证签名
//                if (mySign.equals(wxSign)) {
                returnMap.put("appId", unifiedOrderMap.get("appid"));
                returnMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
                returnMap.put("nonceStr", nonceStr);
                returnMap.put("package", "prepay_id=" + unifiedOrderMap.get("prepay_id"));
                returnMap.put("signType", WXPayConstants.HMACSHA256);
                // 此处生成的签名返回给页面作为参数
                returnMap.put("paySign", WXPayUtil.generateSignature(returnMap, PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET));
                log.info("签名校验成功，下单返回信息为" + returnMap);

                Map<String, Object> storeMap = new HashMap<>();
                // 签名校验成功，你可以在此处进行自己业务逻辑的处理
                // storeMap可以存储那些你需要存进数据库的信息，可以生成预支付订单
                resultMap.put("data", returnMap);
        /*        Trade trade = new Trade();
                trade.setProductId(Integer.parseInt(id));
                trade.setMoney(amount);
                trade.setCreateTime(currDate);
                trade.setBuyerOpenId(buyerOpenId);
                trade.setSellerOpenId(sellerOpenId);
                trade.setPayType(Integer.parseInt(payType));
                log.info("========trade=============" + trade.toString());*/
                JSONObject jObject = new JSONObject();
//                try {
//                    int num = tradeDao.saveTradeInfo(trade);
//                    if (num == 1) {
                resultMap.put("success", true);
                jObject = JSONObject.fromObject(resultMap);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    log.error("保存交易信息异常！" + e);
//                }
                return jObject.toString();
               /* } else {
                    log.error("签名校验失败，下单返回信息为 --> {}", JSONObject.fromObject(resultMap));
                    // 签名校验失败，你可以在此处进行校验失败的业务逻辑
                }*/
            }
        } catch (Exception e) {
            log.error("用户支付，失败", e);
            return null;
        }
        return null;
    }

    @Override
    public void queryTradeList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String openId = user.getOpenId();
        List<Trade> tradeList = tradeDao.queryTradeList(openId, Constants.TRADE_TYPE.NOMAL_TRADE);
        if (tradeList.size() > 0) {
            log.info("=tradeList(0)=======>" + tradeList.get(0).toString());
        }
        session.setAttribute("tradeList", tradeList);
    }

    @Override
    public String oper_cash(HttpServletRequest request, UserService userService) throws Exception {
        HttpSession session = request.getSession();
        String inputMoney = request.getParameter("inputMoney");
        User user = (User) session.getAttribute("user");
        Map<String, String> data = new HashMap<String, String>();
        String nonceStr = WXPayUtil.generateNonceStr();
        String mch_appid = Constants.WECHAT_PARAMETER.APPID;
        String mchid = PayConstants.WECHAT_PAT_CONFIG.MCH_ID;
        String deviceInfo = "WEB";

        data.put("mch_appid", mch_appid);
        data.put("mchid", mchid);
//        data.put("device_info", deviceInfo);
        data.put("nonce_str", nonceStr);

//        data.put("sign_type", WXPayConstants.MD5);

        data.put("partner_trade_no", Constants.DATA_FORMAT.sdf1.format(new Date()));
        data.put("openid", user.getOpenId());
        data.put("check_name", "NO_CHECK");
        data.put("re_user_name", user.getNickName());
        user = userDao.getUserInfoByOpenId(user.getOpenId());
        String serviceCharge = user.getServiceCharge();

        log.info("serviceCharge=====" + serviceCharge);
        if (StringUtils.isEmpty(serviceCharge)) {
            data.put("amount", String.valueOf((int) (Double.parseDouble(inputMoney) * 100 * 0.95)));
        } else {
            double serviceCharge_float = 1 - Utils.percentage2Flooat(serviceCharge);
            data.put("amount", String.valueOf((int) (Double.parseDouble(inputMoney) * 100 * serviceCharge_float)));
        }
        data.put("desc", "提现");
        data.put("spbill_create_ip", WeChatPayUtils.getIp(request));
        String sign = WXPayUtil.generateSignatureMD5(data, PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET);
//        String sign = Utils.getSignCode(data,PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET);
        data.put("sign", sign);
        log.info("提现申请参数:" + data);
        String xmlStr = WXPayUtil.mapToXml(data);
        log.info("转为xml参数:" + xmlStr);
//        JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(Constants.URL.ENTERPRISE_PAY_URL,"POST",xmlStr);
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
//        Map<String, String> tiXianResultMap = wxpay.tixian(data);
        String resultXml = Utils.doRefund("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", xmlStr, mchid);
        log.info("请求提现的返回xml数据:" + resultXml);
        Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
        String returnCode = resultMap.get("return_code");
        String resultCode = resultMap.get("result_code");
        Map<String, Object> returnMap = new HashMap<String, Object>();
        JSONObject jObject = new JSONObject();
        if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
            Trade trade = new Trade();
            // 提现时候设置产品id -999
            trade.setProductId(-999);
            trade.setMoney(inputMoney);
            trade.setCreateTime(new Date());
            trade.setBuyerOpenId(user.getOpenId());
            trade.setPayType(1);
            trade.setTradeType(2);
            log.info("========trade=============" + trade.toString());
            try {
                int num = tradeDao.saveOperCashInfo(trade);
                if (num == 1) {
                    returnMap.put("success", true);
                    userService.getAllMoneyInfo(session, user);
                    returnMap.put("balance", session.getAttribute("balance"));
                    jObject = JSONObject.fromObject(returnMap);
                    log.info("提现成功的json:" + jObject.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("保存交易信息异常！" + e);
            }
        } else {
            returnMap.put("success", false);
            returnMap.put("msg", "财务系统例行维护,请稍后再试");
            jObject = JSONObject.fromObject(returnMap);
        }
        log.info("提现结果:" + jObject.toString());
        return jObject.toString();
    }

    @Override
    public void queryDepositList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Trade> tradeList = tradeDao.queryTradeList(user.getOpenId(), Constants.TRADE_TYPE.DEPOSIT_TRADE);
        for (Trade trade : tradeList) {
            log.info(trade.toString());
        }
        session.setAttribute("depositList", tradeList);
    }

    @Override
    public boolean checkIsPurchased(HttpServletRequest request, String productId, String buyerOpenId) throws Exception {
        int num = tradeDao.checkIsPurchased(productId, buyerOpenId);
        HttpSession session = request.getSession();
        if (num == 1) {
            session.setAttribute("isPurchased", 1);
            log.info(buyerOpenId + "用户已经购买过" + productId + "的产品");
            return true;
        } else {
            session.setAttribute("isPurchased", 0);
            log.info(buyerOpenId + "用户未购买过" + productId + "的产品");
            return false;
        }
    }

    @Override
    public String refundMoney(String balance, String productId, String sellerOpenId) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Product product = productDao.getProductById(Integer.parseInt(productId));
        int count = tradeDao.isRefundByProductId(productId);
        log.info("(余额退款)查询到"+count+"条退款记录");
        if(count>0){
            result.put("success", 2);
            result.put("msg", "该料已经退过款了。");
        }else{
            // 单价
            String price = product.getPrice();
            log.info("产品单价" + price);
            // 哪些人买了
            List<String> buyerOpenIdList = tradeDao.queryBuyerList(productId);
            log.info("买的人数" + buyerOpenIdList.size());
            // 总共需要退款
            double redfundCount = buyerOpenIdList.size() * Double.parseDouble(price);
            log.info("总共需要退款" + redfundCount);
            log.info("余额:" + balance);
            if (Double.parseDouble(balance) >= redfundCount) {
                // 直接余额退款
                for (String buyerOpenId : buyerOpenIdList) {
                    Trade trade = new Trade();
                    trade.setProductId(Integer.parseInt(productId));
                    trade.setMoney(price);
                    trade.setCreateTime(new Date());
                    trade.setBuyerOpenId(sellerOpenId);
                    trade.setSellerOpenId(buyerOpenId);
                    trade.setPayType(0);
                    trade.setTradeType(1);
                    log.info("========trade=============" + trade.toString());
                    tradeDao.saveTradeInfo(trade);
                }
                // 设置产品过期，不能再购买
                productDao.setProductExpritationDateById(productId);
                result.put("success", 1);
            } else {
                result.put("success", false);
            }
        }
log.info("退款（余额）返回的参数为"+(JSONObject.fromObject(result)));
        return (JSONObject.fromObject(result)).toString();
    }

    @Override
    public String refundMoneyByWeChatPay(HttpServletRequest request, String productId, String sellerOpenId) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Product product = productDao.getProductById(Integer.parseInt(productId));
        ServletContext application = request.getSession().getServletContext();
        application.setAttribute("tradeType",1);
        int count = tradeDao.isRefundByProductId(productId);
        log.info("(微信退款)查询到"+count+"条退款记录");
        if(count>0){
            resultMap.put("success", 2);
            resultMap.put("msg", "该料已经退过款了。");
        }else{
            // 单价
            String price = product.getPrice();
            log.info("产品单价" + price);
            // 哪些人买了
            List<String> buyerOpenIdList = tradeDao.queryBuyerList(productId);
            log.info("买的人数" + buyerOpenIdList.size());
            // 总共需要退款
            double redfundCount = buyerOpenIdList.size() * Double.parseDouble(price);
            log.info("总共需要退款" + redfundCount);
            Date currDate = new Date();

            Map<String, String> data = new HashMap<String, String>();
            String nonceStr = WXPayUtil.generateNonceStr();
            String appId = Constants.WECHAT_PARAMETER.APPID;
            String mchId = PayConstants.WECHAT_PAT_CONFIG.MCH_ID;
            String deviceInfo = "WEB";

//        data.put("appid", appId);
//        data.put("mch_id",mchId );
            data.put("device_info", deviceInfo);
//        data.put("nonce_str", nonceStr);
//        data.put("sign", "");
//        data.put("sign_type", WXPayConstants.MD5);
            data.put("body", "退款");
            data.put("detail", "退款");
            data.put("attach", "必赢大数据");
            data.put("out_trade_no", productId+"_1");
            data.put("total_fee", String.valueOf((int) (redfundCount * 100)));
            data.put("spbill_create_ip", WeChatPayUtils.getIp(request));
//        data.put("time_start", Constants.DATA_FORMAT.sdf2.format(currDate));
            data.put("notify_url", domainName + "/trade/paysuccessreturn");
            data.put("trade_type", "JSAPI");
            data.put("openid", sellerOpenId);

            log.info("请求统一下单接口参数:" + data);
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> unifiedOrderMap = wxpay.unifiedOrder(data);

            log.info("调用统一下单接口返回结果:" + unifiedOrderMap);

            String returnCode = unifiedOrderMap.get("return_code");
            String resultCode = unifiedOrderMap.get("result_code");
            try {
                if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                    log.info("微信支付下单成功");
                    // 进行签名校验
                    Map<String, String> map = new HashMap<>();
                    map.put("return_code", unifiedOrderMap.get("return_code"));
                    map.put("return_msg", unifiedOrderMap.get("return_msg"));
                    map.put("appid", unifiedOrderMap.get("appid"));
                    map.put("mch_id", unifiedOrderMap.get("mch_id"));
                    map.put("nonce_str", unifiedOrderMap.get("nonce_str"));
                    map.put("result_code", unifiedOrderMap.get("result_code"));
                    map.put("prepay_id", unifiedOrderMap.get("prepay_id"));
                    map.put("trade_type", unifiedOrderMap.get("trade_type"));
                    // 生成签名
                    String mySign = WXPayUtil.generateSignature(map, PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET);
                    // 微信返回的签名
                    String wxSign = unifiedOrderMap.get("sign");
                    log.info("返回的签名" + wxSign);
                    log.info("最后生成的签名" + mySign);
                    System.out.println("最后生成的签名的参数:" + map);
                    // 需要返回给页面的数据
                    Map<String, String> returnMap = new HashMap<>();
                    // TODO 验证签名
//                if (mySign.equals(wxSign)) {
                    returnMap.put("appId", unifiedOrderMap.get("appid"));
                    returnMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
                    returnMap.put("nonceStr", nonceStr);
                    returnMap.put("package", "prepay_id=" + unifiedOrderMap.get("prepay_id"));
                    returnMap.put("signType", WXPayConstants.HMACSHA256);
                    // 此处生成的签名返回给页面作为参数
                    returnMap.put("paySign", WXPayUtil.generateSignature(returnMap, PayConstants.WECHAT_PAT_CONFIG.MCH_APPSECRET));
                    log.info("签名校验成功，下单返回信息为" + returnMap);

                    Map<String, Object> storeMap = new HashMap<>();
                    // 签名校验成功，你可以在此处进行自己业务逻辑的处理
                    // storeMap可以存储那些你需要存进数据库的信息，可以生成预支付订单
                    resultMap.put("data", returnMap);
                    /*for (String buyerOpenId : buyerOpenIdList) {
                        Trade trade = new Trade();
                        trade.setProductId(Integer.parseInt(productId));
                        trade.setMoney(price);
                        trade.setCreateTime(new Date());
                        trade.setBuyerOpenId(buyerOpenId);
                        trade.setPayType(1);
                        trade.setTradeType(1);
                        log.info("========trade=============" + trade.toString());
                        tradeDao.saveTradeInfo(trade);
                    }*/
                    resultMap.put("success", 1);
                    // 设置产品过期，不能再购买
                    productDao.setProductExpritationDateById(productId);
               /* } else {
                    log.error("签名校验失败，下单返回信息为 --> {}", JSONObject.fromObject(resultMap));
                    // 签名校验失败，你可以在此处进行校验失败的业务逻辑
                }*/
                } else {
                    resultMap.put("success", false);
                    return (JSONObject.fromObject(resultMap)).toString();
                }
            } catch (Exception e) {
                log.error("用户支付，失败", e);
                return null;
            }
        }
        log.info("微信退款参数"+(JSONObject.fromObject(resultMap)).toString());
        return (JSONObject.fromObject(resultMap)).toString();
    }

    @Override
    public void paySuccessReturn(HttpServletRequest request) throws Exception {
        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
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
        inputStream = null;

        Map<String, String> retMap = new HashMap<String, String>();

        String returnCode = map.get("return_code");
        log.info("=======returnCode===========" + returnCode);
        String productId = map.get("out_trade_no");
        productId = productId.split("_")[0];
        log.info("=======productId===========" + productId);
        Product product = productDao.getProductById(Integer.parseInt(productId));
        if ("SUCCESS".equals(returnCode)) {
            ServletContext application = request.getSession().getServletContext();
            String buyerOpenId = (String) application.getAttribute(productId);
            int tradeType = (int) application.getAttribute("tradeType");
            log.info("交易类型"+tradeType);
            if(0 == tradeType){
                log.info("=======buyerOpenId===========" + buyerOpenId);
                String sellerOpenId = product.getOpenId();
                log.info("==product=======" + product.toString());
                Trade trade = new Trade();
                trade.setProductId(Integer.parseInt(productId));
                trade.setMoney(product.getPrice());
                trade.setCreateTime(new Date());
                trade.setBuyerOpenId(buyerOpenId);
                trade.setSellerOpenId(sellerOpenId);
                trade.setPayType(1);
                trade.setTradeType(0);
                log.info("========trade=============" + trade.toString());
                JSONObject jObject = new JSONObject();
                try {
                    int count = tradeDao.isTraded(productId, buyerOpenId, sellerOpenId);
                    if (count > 0) {
                        log.info("该产品已经支付过" + count);
                    } else {
                        int num = tradeDao.saveTradeInfo(trade);
                        if (num == 1) {
                            log.info("回调保存信息成功。");
                        } else {
                            log.info("回调保存信息失败。");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("保存交易信息异常！" + e);
                }
            }else if(1 == tradeType){
                // 哪些人买了
                List<String> buyerOpenIdList = tradeDao.queryBuyerList(productId);
                log.info("(回调)买的人数" + buyerOpenIdList.size());
                // 单价
                String price = product.getPrice();
                log.info("产品单价" + price);
                for (String buyerOpenId1 : buyerOpenIdList) {
                    Trade trade = new Trade();
                    trade.setProductId(Integer.parseInt(productId));
                    trade.setMoney(price);
                    trade.setCreateTime(new Date());
                    trade.setBuyerOpenId(product.getOpenId());
                    trade.setSellerOpenId(buyerOpenId1);
                    trade.setPayType(1);
                    trade.setTradeType(1);
                    log.info("========trade=============" + trade.toString());
                    tradeDao.saveTradeInfo(trade);
                }
                productDao.setProductExpritationDateById(productId);
            }

        }
    }

    public static void main(String[] args) {
        float f = 0.05f;
        double d = 1 - f;
        System.out.println(100 * d);
    }
}


