package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/trade")
public class TradeController {

    private Logger log = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @GetMapping("/pay_weixin")
    public void pay_weixin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = tradeService.pay_weixin(request);
            pw = response.getWriter();
            pw.write(result);
            log.info("支付成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("支付失败" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
