package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.*;
import com.baoliao.weixin.dao.FocusDao;
import com.baoliao.weixin.dao.ProductDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.FocusService;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.util.MessageUtil;
import com.baoliao.weixin.util.Utils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Administrator on 2019\3\4 0004.
 */
@Service
public class FocusServiceImpl implements FocusService {

    private Logger log = LoggerFactory.getLogger(FocusServiceImpl.class);

    @Autowired
    private FocusDao focusDao;
    @Autowired
    private UserDao userDao;

    @Override
    public String focusAuthor(String selfOenId, String otherOpenId) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        FocusInfo focusInfo = new FocusInfo();
        focusInfo.setSelfOpenId(selfOenId);
        focusInfo.setOtherOpenId(otherOpenId);
        focusInfo.setCreateTime(new Date());
        log.info("focusInfo" + focusInfo.toString());
        int num = focusDao.saveFocuInfo(focusInfo);
        log.info("保存成功条数为:" + num);
        if (num == 1) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "关注失败");
        }
        JSONObject jObject = JSONObject.fromObject(result);
        return jObject.toString();
    }

    @Override
    public void getFocusList(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        List<String> focusList = focusDao.getFocusList(user.getOpenId());
        log.info("获取到的关注openId集合:" + focusList.size());
        List<User> userList = new ArrayList<>();
        focusList.forEach(s -> {
            try {
                User u = userDao.getUserInfoByOpenId(s);
                log.info(u.toString());
                userList.add(u);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取关注列表失败。" + e);
            }
        });
        request.getSession().setAttribute("focusList", userList);
    }

    @Override
    public void getFansList(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        List<String> fansList = focusDao.getFansList(user.getOpenId());
        log.info("获取到的粉丝openId集合:" + fansList.size());
        List<User> userList = new ArrayList<>();
        fansList.forEach(s -> {
            try {
                User u = userDao.getUserInfoByOpenId(s);
                log.info(u.toString());
                userList.add(u);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取粉丝列表失败。" + e);
            }
        });
        request.getSession().setAttribute("fansList", userList);
    }
}
