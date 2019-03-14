package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.FocusInfo;
import com.baoliao.weixin.bean.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FocusDao {

    int saveFocuInfo(FocusInfo focusInfo) throws Exception;

    int getFocusByOpenId(String selfOpenId, String otherOpenId) throws Exception;

    int getMyFocusCount(String openId) throws Exception;

    int getFansCount(String openId) throws Exception;

}