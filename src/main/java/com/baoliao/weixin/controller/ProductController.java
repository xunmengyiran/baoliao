package com.baoliao.weixin.controller;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.util.Utils;
import org.apache.commons.collections.bag.SynchronizedBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger log = LoggerFactory.getLogger(ProductController.class);

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
    public void saveProduct(HttpServletRequest request, HttpServletResponse response, @RequestBody Product vo) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            String result = productService.saveProduct(request, vo);
            pw = response.getWriter();
            pw.write(result);
            log.info("保存数据成功:" + result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("保存数据出错" + e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @PostMapping("/img_upload_base64")
    public void uploadImgByBase64(HttpServletRequest request, HttpServletResponse response, String imgdata, String txt) {
        PrintWriter pw = null;
        try {
            String result = productService.uploadImgByBase64(request, imgdata, txt);
            pw = response.getWriter();
            pw.write(result);
        } catch (IOException e1) {
            log.error("上传图片出错1" + e1);
            e1.printStackTrace();
        } catch (Exception e) {
            log.error("上传图片出错2" + e);
        } finally {
            pw.flush();
            pw.close();
        }

    }

    @GetMapping("resultPage")
    public String goGenerateResultPage() {
        return "generate_result";
    }

    @GetMapping("detailInfo")
    public String getDetailInfoByScan(HttpServletRequest request, @RequestParam String id, @RequestParam String price, @RequestParam String openId) {
        String code = request.getParameter("code");
        log.info("扫描二维码获取到的id是:" + id + ",价格是:" + price + ",code是" + code);
        User user = Utils.getUserInfoByCode(request, code);
        if ("0".equals(price) || openId.equals(user.getOpenId())) {
            try {
                productService.getProductDetailInfo(request, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "product_detail_info";
        } else {
            try {
                productService.getPayInfo(request, id, price);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "pay";
        }
    }

    @GetMapping("detailInfo2")
    public String getProductDetailInfoByMoney(HttpServletRequest request, @RequestParam String id) {
        log.info("付费成功后获取到的产品id" + id);
        try {
            productService.getProductDetailInfo(request, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product_detail_info";
    }

    /* *//**
     * 查询买到料列表
     * @param request
     * @return
     *//*
    @GetMapping("getBuyProductList")
    public String getBuyProductList(HttpServletRequest request){
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    *//**
     * 查询买到料列表
     * @param request
     * @return
     *//*
    @GetMapping("getSellerProductList")
    public String getSellerProductList(HttpServletRequest request){
        try {
            productService.getSellerProductList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }*/
}
