package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired

    private ProductService productService;

    /**
     * 初始化用户信息，返回index.jsp
     *
     * @param request
     * @return
     */
    @GetMapping("/goIndex")
    public String goIndex(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        log.info("进入首页" + code);
        if (StringUtils.isNotEmpty(code)) {
            try {
                User user = Utils.getUserInfoByCode(request, code);
            /*boolean isSubscribe = userService.getSubscribeUserByOpenId(user.getOpenId());
            if (!isSubscribe) {
                return "not_subscribe";
            }*/
                userService.updateUserInfo(user);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("进入首页出错,错误信息;" + e);
            }
        }
        return "index";
    }

    @GetMapping("/queryMyCode")
    public String queryMyCode(HttpServletRequest request) {
        log.info("进入我的料码页面");
        try {
            productService.getBuyProductList(request);
            log.info("查询我买的料成功");
            productService.getSellerProductList(request);
            log.info("查询我卖的料成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("进入我的料码页面出错。");
        }
        return "myCode";
    }

    @GetMapping("/queryMyInfo")
    public String queryMyInfo(HttpServletRequest request) {
        log.info("进入我的页面");
        try {
            userService.queryMyInfo(request);
        } catch (Exception e) {
            log.error("进入我页面出错,错误信息;" + e);
        }
        return "myInfo";
    }

    @PostMapping("/deleteBuyRecord")
    public void deleteBuyRecord(HttpServletRequest request, HttpServletResponse response, String id) {
        log.info("获取的id：" + id);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = userService.deleteBuyRecord(id);
            pw = response.getWriter();
            pw.write(result);
            log.info("删除记录成功:" + result);
        } catch (IOException e1) {
            log.error(e1.getMessage(), e1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("删除记录出错" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @GetMapping("/goToSubscribe")
    public String goToSubscribe() {
        return "not_subscribe";
    }
}
