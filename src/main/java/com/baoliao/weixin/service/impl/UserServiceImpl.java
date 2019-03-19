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
        String todayIncome = tradeDao.getTodayIncomeByOpenId(user.getOpenId());
        log.info("当天收益" + todayIncome);
        if (todayIncome == null) {
            todayIncome = "0";
        }
        todayIncome = Utils.conversion2Mumber(todayIncome);

        //收益总额
        String incomeCount = tradeDao.getIncomeByOpenId(user.getOpenId());
        log.info("收益总额" + incomeCount);
        if (incomeCount == null) {
            incomeCount = "0";
        }
        incomeCount = Utils.conversion2Mumber(incomeCount);

        //余额
        //TODO
        // 余额 = 收益总额-支出总额-退款别人金额-提现金额+别人退自己
        // 1.支出总额
        String expenditureCount = tradeDao.getExpenditureByOpenId(user.getOpenId());
        log.info("支出总额" + incomeCount);
        if (expenditureCount == null) {
            expenditureCount = "0";
        }
        expenditureCount = Utils.conversion2Mumber(expenditureCount);
        //2.退款(1.退给别人，2，别人退给自己)
        String refundToOtherCount = tradeDao.getRefundToOtherByOpenId(user.getOpenId());
        log.info("退别人总额" + incomeCount);
        if (refundToOtherCount == null) {
            refundToOtherCount = "0";
        }
        refundToOtherCount = Utils.conversion2Mumber(refundToOtherCount);
        String refundToSelfCount = tradeDao.getRefundToSelfByOpenId(user.getOpenId());
        log.info("别人退自己总额" + refundToSelfCount);
        if (refundToSelfCount == null) {
            refundToSelfCount = "0";
        }
        refundToSelfCount = Utils.conversion2Mumber(refundToSelfCount);
        //3.提现
        String depositCount = tradeDao.getDepositByOpenId(user.getOpenId());
        log.info("提现总额" + depositCount);
        if (depositCount == null) {
            depositCount = "0";
        }
        depositCount = Utils.conversion2Mumber(depositCount);

        double balance = Double.parseDouble(incomeCount) - Double.parseDouble(expenditureCount) - Double.parseDouble(refundToOtherCount) - Double.parseDouble(depositCount) + Double.parseDouble(refundToSelfCount);
        session.setAttribute("balance", balance);

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
