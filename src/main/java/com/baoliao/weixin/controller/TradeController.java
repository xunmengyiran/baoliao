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

    @RequestMapping("/oper_cash")
    public void oper_cash(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = tradeService.oper_cash(request);
            pw = response.getWriter();
            pw.write(result);
            log.info("提现成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("提现失败" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @RequestMapping("/tradeList")
    public String queryTradeList(HttpServletRequest request) {
        try {
            tradeService.queryTradeList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "trade_list";
    }

    @RequestMapping("/depositList")
    public String queryDepositList(HttpServletRequest request) {
        try {
            tradeService.queryDepositList(request);
            log.info("查询提现列表成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询提现列表失败！");
        }
        return "deposit_list";
    }
}
