package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.User;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {

    String saveProduct(HttpServletRequest request, Product record) throws Exception;

    String uploadImgByBase64(HttpServletRequest request, String imgData, String format) throws Exception;

    void getPayInfo(HttpServletRequest request, String id, String price, User buyer_user) throws Exception;

    void getBuyProductList(HttpServletRequest request) throws Exception;

    void getSellerProductList(HttpServletRequest request) throws Exception;

    Product getProductDetailInfo(HttpServletRequest request, String id) throws Exception;

    void getSellerProductDetail(HttpServletRequest request, String id) throws Exception;

}
