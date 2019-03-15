package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.Trade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeDao {

    int saveTradeInfo(Trade trade) throws Exception;

    String getTodayIncomeByopenId(String openId) throws Exception;

    String getIncomeCountByopenId(String openId) throws Exception;

    List<Trade> queryTradeList(String openId) throws Exception;
}