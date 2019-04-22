package com.baoliao.weixin.service;

import javax.servlet.http.HttpServletRequest;

public interface TradeService {

    String payByBalance(HttpServletRequest request) throws Exception;

    String payByWeixin(HttpServletRequest request) throws Exception;

    void queryTradeList(HttpServletRequest request) throws Exception;

    String oper_cash(HttpServletRequest request, UserService userService) throws Exception;

    void queryDepositList(HttpServletRequest request) throws Exception;

    /**
     * 检查是否已经购买过
     *
     * @param request
     * @param productId
     * @param buyerOpenId
     * @return
     * @throws Exception
     */
    boolean checkIsPurchased(HttpServletRequest request, String productId, String buyerOpenId) throws Exception;

    String refundMoney(String balance, String productId, String sellerOpenId) throws Exception;

    String refundMoneyByWeChatPay(HttpServletRequest request, String productId, String sellerOpenId) throws Exception;
}
