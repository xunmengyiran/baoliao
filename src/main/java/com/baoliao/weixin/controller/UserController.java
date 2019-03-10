package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 初始化用户信息，返回index.jsp
     *
     * @param request
     * @return
     */
    @GetMapping("/goIndex")
    public String goIndex(HttpServletRequest request) {
        try {
//            List<User> userList = userService.goIndex();
//            request.setAttribute("userList", userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/queryMyCode")
    public String queryMyCode() {
        return "myCode";
    }

    @GetMapping("/queryMyInfo")
    public String queryMyInfo(HttpServletRequest request) {
        try {
            userService.queryMyInfo(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "myInfo";
    }
}
