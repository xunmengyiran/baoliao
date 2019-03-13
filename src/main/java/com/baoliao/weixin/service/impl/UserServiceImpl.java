package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.controller.UserController;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
import com.baoliao.weixin.util.WeixinIntefaceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public List<User> goIndex() {
        List<User> userList = userDao.queryUserList();
        return userDao.queryUserList();
    }

    @Override
    public int updateUserInfo(HttpServletRequest request, String code) throws Exception {
        User user = Utils.getUserInfoByCode(code);
        request.getSession().setAttribute("user", user);
        userDao.updateUserInfo(user);
        return 0;
    }

    @Override
    public User queryMyInfo(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        User user = (User) session.getAttribute("user");
        log.info("从session中获取的user:" + user);
        if (user == null) {
            user = Utils.getUserInfoByCode(code);
            // 由于code只能使用一次，所以将用户信息存入session
            request.getSession().setAttribute("user", user);
        }
        return user;
    }
}
