package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.bean.Trade;
import com.baoliao.weixin.dao.TradeDao;
import com.baoliao.weixin.service.TradeService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
        trade.setPayType(payType);
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
}
