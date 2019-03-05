package com.baoliao.weixin.controller;

import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 保存首页数据
     *
     * @param request
     * @param response
     * @param vo
     */
    @PostMapping("/save")
    public void queryUserList(HttpServletRequest request, HttpServletResponse response, @RequestBody Product vo) {
        PrintWriter pw = null;
        String result = productService.insertSelective(request, vo);
        try {
            pw = response.getWriter();
            pw.write(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
