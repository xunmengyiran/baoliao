package com.baoliao.weixin.service;

import javax.servlet.http.HttpServletRequest;

public interface TradeService {

    String pay_weixin(HttpServletRequest request) throws Exception;

    void queryTradeList(HttpServletRequest request) throws Exception;
}
