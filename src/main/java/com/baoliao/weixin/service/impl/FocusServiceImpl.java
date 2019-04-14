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
import org.springframework.transaction.annotation.Transactional;
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<FocusInfo> focusList = focusDao.getFocusList(user.getOpenId());
        log.info("获取到的关注openId集合:" + focusList.size());
        List<User> userList = new ArrayList<>();
        for (FocusInfo focusInfo : focusList) {
            log.info("获取到的关注:" + focusInfo.toString());
            try {
                User u = userDao.getUserInfoByOpenId(focusInfo.getOtherOpenId());
                u.setSubscribeTime(focusInfo.getCreateTime());
                log.info(u.toString());
                userList.add(u);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取关注列表失败。" + e);
            }
        }
        session.setAttribute("focusList", userList);
    }

    @Override
    public void getFansList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<FocusInfo> fansList = focusDao.getFansList(user.getOpenId());
        log.info("获取到的粉丝openId集合:" + fansList.size());
        List<User> userList = new ArrayList<>();
        for (FocusInfo focusInfo : fansList) {
            log.info("获取到的粉丝:" + focusInfo.toString());
            try {
                User u = userDao.getUserInfoByOpenId(focusInfo.getSelfOpenId());
                u.setSubscribeTime(focusInfo.getCreateTime());
                log.info(u.toString());
                userList.add(u);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取粉丝列表失败。" + e);
            }
        }
        session.setAttribute("fansList", userList);
    }

    /**
     * 取消关注
     *
     * @param request
     * @param otherOpenId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public String cancelAttention(HttpServletRequest request, String otherOpenId) throws Exception {
        HttpSession session = request.getSession();
        //获取当前用户信息
        User user = (User) session.getAttribute("user");
        Map<String, Object> model = new HashMap<String, Object>();
        FocusInfo focusInfo = new FocusInfo();
        focusInfo.setSelfOpenId(user.getOpenId());
        focusInfo.setOtherOpenId(otherOpenId);
        focusInfo.setIsCancel(1);
        focusInfo.setCancelTime(new Date());
        System.out.println("=========focusInfo============" + focusInfo.toString());
        int i = 0;
        try {
            i = focusDao.cancelAttention(focusInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (i > 0) {
            model.put("result", i);
            model.put("msg", "成功");
        } else {
            model.put("result", i);
            model.put("msg", "更新数据出现异常:" + i);
        }
        JSONObject jObject = JSONObject.fromObject(model);
        return jObject.toString();
    }
}
