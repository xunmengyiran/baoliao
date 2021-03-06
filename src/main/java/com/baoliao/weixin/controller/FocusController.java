package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.FocusInfo;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.service.FocusService;
import com.baoliao.weixin.service.ProductService;
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
@RequestMapping("/focus")
public class FocusController {

    private Logger log = LoggerFactory.getLogger(FocusController.class);

    @Autowired
    private FocusService focusService;

    @PostMapping("/focusAuthor")
    public void focusAuthor(@RequestParam String selfOenId, @RequestParam String otherOpenId, HttpServletResponse response) {
        log.info("selfOenId===>" + selfOenId);
        log.info("selfOenId===>" + otherOpenId);
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = focusService.focusAuthor(selfOenId, otherOpenId);
            pw = response.getWriter();
            pw.write(result);
            log.info("关注作者成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("关注作者出错" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @GetMapping("/getFocusList")
    public String getFocusList(HttpServletRequest request) {
        try {
            focusService.getFocusList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "focus_list";
    }

    @GetMapping("/getFansList")
    public String getFansList(HttpServletRequest request) {
        try {
            focusService.getFansList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fans_list";
    }

    /**
     * 取消关注
     *
     * @param request
     * @return
     */
    @PostMapping("/cancelAttention")
    public void cancelAttention(HttpServletRequest request, HttpServletResponse response, String otherOpenId) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = focusService.cancelAttention(request, otherOpenId);
            pw = response.getWriter();
            pw.write(result);
            log.info("更新数据成功:" + result);
        } catch (IOException e1) {
            log.error(e1.getMessage(), e1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("更新数据出错" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
