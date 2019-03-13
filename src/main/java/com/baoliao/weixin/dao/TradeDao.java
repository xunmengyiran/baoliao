package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.Trade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeDao {

    int saveTradeInfo(Trade trade) throws Exception;
}