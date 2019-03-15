package com.baoliao.weixin.service;


import javax.servlet.http.HttpServletRequest;

public interface FocusService {

    String focusAuthor(String selfOenId, String otherOpenId) throws Exception;

    void getFocusList(HttpServletRequest request) throws Exception;

    void getFansList(HttpServletRequest request) throws Exception;

}
