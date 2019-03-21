package com.baoliao.weixin.dao;
import com.baoliao.weixin.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {

    int saveProduct(Product product) throws Exception;

    int updateQRImgNameById(@Param("fileName") String fileName, @Param("id") int id) throws Exception;

    Product getProductById(int id) throws Exception;
}