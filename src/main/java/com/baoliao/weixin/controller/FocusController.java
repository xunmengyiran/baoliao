package com.baoliao.weixin.controller;

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
}
