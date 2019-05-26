package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.service.TradeService;
import com.baoliao.weixin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/trade")
public class TradeController {

    private Logger log = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;

    @GetMapping("/pay_balance")
    public void payByBalance(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = tradeService.payByBalance(request);
            pw = response.getWriter();
            pw.write(result);
            log.info("支付成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付失败" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @GetMapping("/pay_weixin")
    public void payByWeixin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = tradeService.payByWeixin(request);
            pw = response.getWriter();
            pw.write(result);
            log.info("支付成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付失败" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @RequestMapping("/paysuccessreturn")
    public void paySuccessReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            tradeService.paySuccessReturn(request);
        } catch (Exception e) {
            e.printStackTrace();
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
            String result = tradeService.oper_cash(request, userService);
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

    @RequestMapping("/refundMoneyByBalance")
    public void refundMoneyByBalance(HttpServletRequest request, HttpServletResponse response, String productId) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        log.info("开始申请一键退款,退款的产品(余额支付):" + productId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String sellerOpenId = user.getOpenId();
        try {
            userService.getAllMoneyInfo(session, user);
            String balance = (String) session.getAttribute("balance");
            String result = tradeService.refundMoney(balance, productId, sellerOpenId);
            pw = response.getWriter();
            pw.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @RequestMapping("/refundMoneyByWeChatPay")
    public void refundMoneyByWeChatPay(HttpServletRequest request, HttpServletResponse response, String productId) {
        PrintWriter pw = null;
        log.info("开始申请一键退款,退款的产品(微信支付):" + productId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String sellerOpenId = user.getOpenId();
        try {
            String result = tradeService.refundMoneyByWeChatPay(request, productId, sellerOpenId);
            pw = response.getWriter();
            pw.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
