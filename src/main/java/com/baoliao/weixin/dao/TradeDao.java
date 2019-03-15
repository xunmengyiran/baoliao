package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.Trade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeDao {

    int saveTradeInfo(Trade trade) throws Exception;

    String getTodayIncomeByopenId(String openId) throws Exception;

    String getIncomeCountByopenId(String openId) throws Exception;
}