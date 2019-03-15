package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.dao.FocusDao;
import com.baoliao.weixin.dao.TradeDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
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

    @Autowired
    TradeDao tradeDao;

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
        //当天收益额
        String todayIncome = tradeDao.getTodayIncomeByopenId(user.getOpenId());
        log.info("当天收益" + todayIncome);
        if (todayIncome == null) {
            todayIncome = "0";
        }
        todayIncome = Utils.conversion2Mumber(todayIncome);

        //收益总额
        String incomeCount = tradeDao.getIncomeCountByopenId(user.getOpenId());
        log.info("收益总额" + incomeCount);
        if (incomeCount == null) {
            incomeCount = "0";
        }
        incomeCount = Utils.conversion2Mumber(incomeCount);

        //余额
        //TODO
        // 余额 = 收益总额-支出总额-退款金额-提现金额


        // 查询我关注的总数
        int focusCount = focusDao.getMyFocusCount(user.getOpenId());
        // 查询我的粉丝总数
        int fansCount = focusDao.getFansCount(user.getOpenId());
        session.setAttribute("todayIncome", todayIncome);
        session.setAttribute("incomeCount", incomeCount);
        session.setAttribute("focusCount", focusCount);
        session.setAttribute("fansCount", fansCount);
        return user;
    }
}
