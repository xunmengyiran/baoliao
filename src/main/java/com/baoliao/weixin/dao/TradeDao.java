package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeDao {

    int saveTradeInfo(Trade trade) throws Exception;

    String getTodayIncomeByOpenId(String openId) throws Exception;

    String getIncomeByOpenId(String openId) throws Exception;

    String getExpenditureByOpenId(String openId) throws Exception;

    String getRefundToOtherByOpenId(String openId) throws Exception;

    String getRefundToSelfByOpenId(String openId) throws Exception;

    String getDepositByOpenId(String openId) throws Exception;


    List<Trade> queryTradeList(@Param("openId") String openId, @Param("tradeType") int tradeType) throws Exception;

    List<Trade> queryBuyProductList(String openId) throws Exception;

    List<Trade> querySellerProductList(String openId) throws Exception;

    int saveOperCashInfo(Trade trade) throws Exception;

    int checkIsPurchased(@Param("productId") String productId, @Param("buyerOpenId") String buyerOpenId) throws Exception;

    List<String> queryBuyerList(@Param("productId") String productId) throws Exception;

    int isTraded(@Param("productId") String productId,@Param("buyerOpenId") String buyerOpenId,@Param("sellerOpenId") String sellerOpenId) throws Exception;
    int isRefundByProductId(@Param("productId") String productId) throws Exception;

    int isHaveRefundRecord(@Param("productId")String productId,@Param("buyerOpenId")String buyerOpenId,@Param("sellerOpenId") String sellerOpenId) throws Exception;
}