package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.Product;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {

    String saveProduct(HttpServletRequest request, Product record) throws Exception;

    String uploadImgByBase64(HttpServletRequest request, String imgData, String format) throws Exception;
}
