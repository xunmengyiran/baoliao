package com.baoliao.weixin.service;


import com.baoliao.weixin.bean.FocusInfo;

import javax.servlet.http.HttpServletRequest;

public interface FocusService {

    String focusAuthor(String selfOenId, String otherOpenId) throws Exception;

    void getFocusList(HttpServletRequest request) throws Exception;

    void getFansList(HttpServletRequest request) throws Exception;

    String cancelAttention(HttpServletRequest request, String otherOpenId) throws Exception;

}
