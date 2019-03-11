package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.controller.UserController;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.UserService;
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
    public User queryMyInfo(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        User user = (User) session.getAttribute("user");
        log.info("从session中获取的user:" + user);
        if (user == null) {
            String oauth2_token_url = Constants.URL.OAUTH2_ACCESS_TOKEN.replace("APPID", Constants.WECHAT_PARAMETER.APPID).replace("SECRET", Constants.WECHAT_PARAMETER.APPSECRET).replace("CODE", code);
            JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(oauth2_token_url, "GET", null);
            log.info("jsonObject1:" + jsonObject);
            openId = jsonObject.getString("openid");
            String access_token = jsonObject.getString("access_token");
            String userinfourl = Constants.URL.OAUTH2_USERINFO_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
            jsonObject = WeixinIntefaceUtil.httpRequest(userinfourl, "GET", null);
            log.info("jsonObject2:" + jsonObject);
            String nickName = jsonObject.getString("nickname");
            String sex = jsonObject.getString("sex");
            String language = jsonObject.getString("language");
            String city = jsonObject.getString("city");
            String province = jsonObject.getString("province");
            String country = jsonObject.getString("country");
            String headImgUrl = jsonObject.getString("headimgurl");

            user = new User();
            user.setOpenId(openId);
            user.setNickName(nickName);
            user.setSex(Integer.parseInt(sex));
            user.setLanguage(language);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setHeadimgUrl(headImgUrl);
            // 由于code只能使用一次，所以将用户信息存入session
            request.getSession().setAttribute("user", user);
        }
        return user;
    }
}
