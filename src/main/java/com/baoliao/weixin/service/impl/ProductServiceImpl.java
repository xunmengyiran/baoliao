package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.*;
import com.baoliao.weixin.dao.FocusDao;
import com.baoliao.weixin.dao.ProductDao;
import com.baoliao.weixin.dao.TradeDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.util.MessageUtil;
import com.baoliao.weixin.util.Utils;
import com.baoliao.weixin.util.WeixinIntefaceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019\3\4 0004.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${domain_name}")
    private String domainName;

    @Value("${product_img_path}")
    private String productImgPath;

    @Value("${qr_code_img_path}")
    private String qrCodeImgPath;

    @Value("${spring.profiles.active}")
    protected String activeProfile;


    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FocusDao focusDao;

    @Autowired
    TradeDao tradeDao;


    @Override
    public String saveProduct(HttpServletRequest request, Product vo) {
        String code = vo.getCode();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = Utils.getUserInfoByCode(request, code);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(user.getOpenId())) {
            vo.setOpenId(user.getOpenId());
        }

        int i = 0;
        try {
            vo.setCreateTime(new Date());
            i = productDao.saveProduct(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            model.put("result", i);
            model.put("msg", "成功");
        } else {
            model.put("result", i);
            model.put("msg", "保存数据出现异常:" + i);
        }
        log.info("读取的域名配置是:" + domainName);
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String path = qrCodeImgPath;
        String logoPath = qrCodeImgPath + "logo.png";
        if ("dev".equals(activeProfile)) {
            path = file.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "QRCodeImg" + File.separator;
            logoPath = file.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "img" + File.separator + "logo.png";
        }
        log.info("保存时，环境是" + activeProfile + ",存储路径是" + path);
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http://" + redirect_domain_name + "/user/goIndex&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
        String fileName = Utils.zxingCodeCreate("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.WECHAT_PARAMETER.APPID + "&redirect_uri=" + domainName + "/product/detailInfo%3Fid%3D" + vo.getId() + "%26price%3D" + vo.getPrice() + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect", path, 250, logoPath);
        log.info("生成的二维码名称:" + fileName);
        try {
            productDao.updateQRImgNameById(vo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("更新qr_img_name成功。");
        sendSaveSuccessMessage(vo);
        session.setAttribute("fileName", fileName + ".jpg");
        session.setAttribute("product", vo);
        JSONObject jObject = JSONObject.fromObject(model);
        return jObject.toString();
    }

    @Override
    public String uploadImgByBase64(HttpServletRequest request, String imgData, String format) throws FileNotFoundException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String currDateStr = Constants.DATA_FORMAT.sdf1.format(new Date());
        Random random = new Random();
        String fileName = currDateStr + String.valueOf(random.nextInt(1000));
        resultMap.put("success", true);
        resultMap.put("data", fileName + format);
        String path = productImgPath;
        if ("dev".equals(activeProfile)) {
            File file = new File(ResourceUtils.getURL("classpath:").getPath());
            path = file.getParentFile().getParentFile() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "userImg" + File.separator;
        }
        log.info("上传图片时环境是" + activeProfile + ",存储路径是" + path);
        log.info("文件名是:" + fileName);
        Utils.GenerateImage(imgData, fileName, path, format);
        JSONObject jObject = JSONObject.fromObject(resultMap);
        return jObject.toString();
    }

    @Override
    public void getPayInfo(HttpServletRequest request, String id, String price) throws Exception {
        log.info("传入的产品id为:" + id);
        String code = request.getParameter("code");
        User buyer_user = Utils.getUserInfoByCode(request, code);
        log.info("============buyer_user=============" + buyer_user.toString());
        Product product = productDao.getProductById(Integer.parseInt(id));
        log.info("============product=============" + product.toString());
        User seller_user = userDao.getUserInfoByOpenId(product.getOpenId());
        log.info("============seller_user=============" + seller_user.toString());
        request.getSession().setAttribute("product", product);
        request.getSession().setAttribute("buyer_user", buyer_user);
        request.getSession().setAttribute("seller_user", seller_user);
        //TODO
        //判段是否是关注的作者
        int count = focusDao.getFocusByOpenId(buyer_user.getOpenId(), seller_user.getOpenId());
        if (count == 0) {
            request.getSession().setAttribute("focus", 0);
        } else {
            request.getSession().setAttribute("focus", 1);
        }

    }


    public void sendSaveSuccessMessage(Product product) {
        log.info("准备发送模板信息");
        WeChatTemplate weChatTemplate = new WeChatTemplate();
        // TODO
        // 模板ID
        weChatTemplate.setTemplate_id("C2AsLa9MzW-rNDsBbjc2D8X7TkH4XZG2xaMsOeX7GzM");
        // 需要推送的用户openId
        weChatTemplate.setTouser(product.getOpenId());
        //点击跳转的链接
        weChatTemplate.setUrl("http://cailiao.bingbet.net/user/queryMyCode");

        Map<String, TemplateData> data = new HashMap<>();

        TemplateData first = new TemplateData();
        first.setValue("作品制作完成通知");
        first.setColor("#173177");
        data.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue(product.getTitle());
        keyword1.setColor("#173177");
        data.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue(Constants.DATA_FORMAT.sdf2.format(product.getCreateTime()));
        keyword2.setColor("#173177");
        data.put("keyword2", keyword2);


        TemplateData remark = new TemplateData();
        remark.setValue("请点击'我的作品'查看");
        remark.setColor("#173177");
        data.put("remark", remark);

        weChatTemplate.setData(data);
        JSONObject json = JSONObject.fromObject(weChatTemplate);
        log.info("模板消息组装完成:" + json.toString());
        MessageUtil.sendCustomerService(json.toString());
    }

    /**
     * 查询买到的料列表
     *
     * @param request
     * @throws Exception
     */
    @Override
    public void getBuyProductList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        User user1 = new User();
        user1.setOpenId("ohDAp1PJ7rxxLGZIoKbN1T2UllIo");
        String openId = user1.getOpenId();
        List<Trade> buyProductList = tradeDao.queryBuyProductList(openId);
        session.setAttribute("buyProductList", buyProductList);
    }

    /**
     * 查询卖到料列表
     *
     * @param request
     * @throws Exception
     */
    @Override
    public void getSellerProductList(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        User user1 = new User();
        user1.setOpenId("ohDAp1MbpmyfnexLVONp2xCCTt-Q");
        List<Trade> sellerProductList = tradeDao.querySellerProductList(user1.getOpenId());
        session.setAttribute("sellerProductList", sellerProductList);
    }
}
