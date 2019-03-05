package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.dao.ProductDao;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.util.WeixinIntefaceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019\3\4 0004.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public String insertSelective(HttpServletRequest request, Product vo) {
        String code = vo.getCode();
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        if(StringUtils.isEmpty(openId)) {
            String oauth2_token_url = Constants.URL.OAUTH2_ACCESS_TOKEN.replace("APPID", Constants.WECHAT_PARAMETER.APPID).replace("SECRET", Constants.WECHAT_PARAMETER.APPSECRET).replace("CODE", code);
            JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(oauth2_token_url, "GET", null);
            openId = jsonObject.getString("openid");
            session.setAttribute("openId",openId);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(openId)) {
            vo.setOpenId(openId);
        }

        int i = productDao.insertSelective(vo);
        if (i > 0) {
            model.put("result", i);
            model.put("msg","成功");
        }else{
            model.put("result", i);
            model.put("msg","保存数据出现异常:"+i);
        }
        JSONObject jObject = JSONObject.fromObject(model);
        return jObject.toString();
    }
}
