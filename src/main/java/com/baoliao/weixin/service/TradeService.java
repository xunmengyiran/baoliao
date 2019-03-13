package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.Product;

import javax.servlet.http.HttpServletRequest;

public interface TradeService {

    String pay_weixin(HttpServletRequest request);

}
