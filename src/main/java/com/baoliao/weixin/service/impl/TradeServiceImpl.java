package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Trade;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.dao.TradeDao;
import com.baoliao.weixin.service.TradeService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    private Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    TradeDao tradeDao;

    @Override
    public String pay_weixin(HttpServletRequest request) {
        String buyerOpenId = request.getParameter("buyerOpenId");
        String sellerOpenId = request.getParameter("sellerOpenId");
        String amount = request.getParameter("amount");
        String payType = request.getParameter("type");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");

        Trade trade = new Trade();
        trade.setProductId(Integer.parseInt(id));
        trade.setMoney(amount);
        trade.setCreateTime(new Date());
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
        //TODO 模拟支付成功

        return jObject.toString();
    }

    @Override
    public void queryTradeList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String openId = user.getOpenId();
        List<Trade> tradeList = tradeDao.queryTradeList(openId, Constants.TRADE_TYPE.NOMAL_TRADE);
        session.setAttribute("tradeList", tradeList);
    }

    @Override
    public String oper_cash(HttpServletRequest request) throws Exception {
        String inputMoney = request.getParameter("inputMoney");
        User user = (User) request.getSession().getAttribute("user");
        Trade trade = new Trade();
        // 提现时候设置产品id -999
        trade.setProductId(-999);
        trade.setMoney(inputMoney);
        trade.setCreateTime(new Date());
        trade.setBuyerOpenId("");
        trade.setBuyerOpenId(user.getOpenId());
        trade.setPayType(1);
        trade.setTradeType(2);
        log.info("========trade=============" + trade.toString());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject jObject = new JSONObject();
        try {
            int num = tradeDao.saveOperCashInfo(trade);
            if (num == 1) {
                resultMap.put("success", true);
                jObject = JSONObject.fromObject(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存交易信息异常！" + e);
        }
        //TODO 模拟提现成功

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
}
