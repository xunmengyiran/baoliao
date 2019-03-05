package com.baoliao.weixin.service;

import com.baoliao.weixin.bean.Product;
import javax.servlet.http.HttpServletRequest;

public interface ProductService {

    String insertSelective(HttpServletRequest request,Product record);
}
