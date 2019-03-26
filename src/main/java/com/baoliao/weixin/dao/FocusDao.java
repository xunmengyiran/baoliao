package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.FocusInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FocusDao {

    int saveFocuInfo(FocusInfo focusInfo) throws Exception;

    int getFocusByOpenId(@Param("selfOpenId") String selfOpenId, @Param("otherOpenId") String otherOpenId) throws Exception;

    int getMyFocusCount(String openId) throws Exception;

    int getFansCount(String openId) throws Exception;

    List<String> getFocusList(String openId) throws Exception;

    List<String> getFansList(String openId) throws Exception;

    int cancelAttention(FocusInfo focusInfo) throws Exception;

}