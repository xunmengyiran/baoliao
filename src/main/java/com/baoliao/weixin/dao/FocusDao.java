package com.baoliao.weixin.dao;

import com.baoliao.weixin.bean.FocusInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FocusDao {

    int saveFocuInfo(FocusInfo focusInfo) throws Exception;

    int getFocusByOpenId(String selfOpenId, String otherOpenId) throws Exception;

    int getMyFocusCount(String openId) throws Exception;

    int getFansCount(String openId) throws Exception;

    List<String> getFocusList(String openId) throws Exception;

    List<String> getFansList(String openId) throws Exception;

}