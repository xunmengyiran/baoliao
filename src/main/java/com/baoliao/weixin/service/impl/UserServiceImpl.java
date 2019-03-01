package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> queryUserList() {
        System.out.println("进入service");
        List<User> userList = userDao.queryUserList();
        System.out.println("=>>>>"+userList.size());
        return userDao.queryUserList();
    }
}
