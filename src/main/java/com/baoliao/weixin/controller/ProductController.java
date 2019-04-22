package com.baoliao.weixin.controller;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.service.TradeService;
import com.baoliao.weixin.service.UserService;
import com.baoliao.weixin.util.Utils;
import org.apache.commons.collections.bag.SynchronizedBag;
import org.apache.commons.lang.StringUtils;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private TradeService tradeService;
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

    @GetMapping("/resultPage")
    public String goGenerateResultPage() {
        return "generate_result";
    }

    @GetMapping("/detailInfo")
    public String getDetailInfoByScan(HttpServletRequest request, @RequestParam String id, @RequestParam String price) {
        String code = request.getParameter("code");
        log.info("扫描二维码获取到的id是:" + id + ",价格是:" + price + ",code是" + code);
        Product product = new Product();
        User buyer_user = Utils.getUserInfoByCode(request, code);
        String buyerOpenId = buyer_user.getOpenId();
        try {
            product = productService.getProductDetailInfo(request, buyer_user, id);
            // 判断是否过期
            String expritationDateStr = product.getExpritationDate();
            log.info("过期时间为:" + expritationDateStr);
            if (StringUtils.isNotEmpty(expritationDateStr)) {
                if (new Date().after(Constants.DATA_FORMAT.sdf3.parse(expritationDateStr))) {
                    request.getSession().setAttribute("isScan", 1);
                    return "product_detail_info";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            boolean isPurchased = tradeService.checkIsPurchased(request, id, buyerOpenId);
            /*boolean isSubscribe = userService.getSubscribeUserByOpenId(buyerOpenId);
            if (!isSubscribe) {
                return "not_subscribe";
            } else*/
            if ("0".equals(price) || product.getOpenId().equals(buyerOpenId) || isPurchased) {
                try {
                    productService.getProductDetailInfo(request, buyer_user, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "product_detail_info";
            } else {
                try {
                    productService.getPayInfo(request, id, price, buyer_user);
                    userService.getAllMoneyInfo(request.getSession(), buyer_user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "pay";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/detailInfo2")
    public String getProductDetailInfoByMoney(HttpServletRequest request, @RequestParam String id) {
        log.info("付费成功后获取到的产品id" + id);
        try {
            User user = (User) request.getSession().getAttribute("user");
            Product product = productService.getProductDetailInfo(request, user, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product_detail_info";
    }

    @GetMapping("/sellerproductdetail")
    public String getSellerProductDetail(HttpServletRequest request, @RequestParam("id") String id) {
        try {
            productService.getSellerProducQRimg(request, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "generate_result";
    }

}
