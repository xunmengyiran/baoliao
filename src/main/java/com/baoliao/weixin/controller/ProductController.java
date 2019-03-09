package com.baoliao.weixin.controller;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.service.ProductService;
import org.apache.commons.collections.bag.SynchronizedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        try {
            String result = productService.insertSelective(request, vo);
            pw = response.getWriter();
            pw.write(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @PostMapping("/img_upload_base64")
    public synchronized void uploadImgByBase64(HttpServletRequest request, HttpServletResponse response, String imgdata, String txt) {
        PrintWriter pw = null;
        try {
            String result = productService.uploadImgByBase64(request, imgdata, txt);
            pw = response.getWriter();
            pw.write(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }

    }
}
