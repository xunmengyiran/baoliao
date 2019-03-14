package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.dao.FocusDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
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
    @Autowired
    FocusDao focusDao;

    @Override
    public List<User> goIndex() {
        List<User> userList = userDao.queryUserList();
        return userDao.queryUserList();
    }

    @Override
    public int updateUserInfo(HttpServletRequest request, String code) throws Exception {
        User user = Utils.getUserInfoByCode(request, code);
        userDao.updateUserInfo(user);
        return 0;
    }

    @Override
    public User queryMyInfo(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
//        String openId = (String) session.getAttribute("openId");
        User user = (User) session.getAttribute("user");
        log.info("从session中获取的user:" + user);
        if (user == null) {
            user = Utils.getUserInfoByCode(request, code);
        }
        // 查询我关注的总数
        int focusCount = focusDao.getMyFocusCount(user.getOpenId());
        // 查询我的粉丝总数
        int fansCount = focusDao.getFansCount(user.getOpenId());
        request.getSession().setAttribute("focusCount", focusCount);
        request.getSession().setAttribute("fansCount", fansCount);
        return user;
    }
}
