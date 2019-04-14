package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    List<User> goIndex() throws Exception;

    int updateUserInfo(HttpServletRequest request, String code) throws Exception;

    User queryMyInfo(HttpServletRequest request) throws Exception;

    void getAllMoneyInfo(HttpSession session, User user) throws Exception;

    boolean getSubscribeUserByOpenId(String openId) throws Exception;

    String deleteBuyRecord(String id) throws Exception;
}
