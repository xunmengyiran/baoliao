package com.baoliao.weixin.dao;
import com.baoliao.weixin.bean.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao {

    int saveProduct(Product product) throws Exception;

    int updateQRImgNameById(int id) throws Exception;

    String getOpenIdById(String id) throws Exception;
}