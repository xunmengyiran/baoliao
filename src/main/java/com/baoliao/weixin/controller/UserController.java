package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/queryUserList")
    public String queryUserList(Model model){
        System.out.println("进入queryUserList====controller==>>>");
        List<User> userList = userService.queryUserList();
        model.addAttribute("userList",userList);
        return "index";
    }
}
