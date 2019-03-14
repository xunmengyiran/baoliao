package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.Product;

import javax.servlet.http.HttpServletRequest;

public interface FocusService {

    String focusAuthor(String selfOenId, String otherOpenId) throws Exception;

}
