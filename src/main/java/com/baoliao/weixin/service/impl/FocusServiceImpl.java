package com.baoliao.weixin.service.impl;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.Product;
import com.baoliao.weixin.bean.TemplateData;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.bean.WeChatTemplate;
import com.baoliao.weixin.dao.ProductDao;
import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.service.FocusService;
import com.baoliao.weixin.service.ProductService;
import com.baoliao.weixin.util.MessageUtil;
import com.baoliao.weixin.util.Utils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2019\3\4 0004.
 */
@Service
public class FocusServiceImpl implements FocusService {

    private Logger log = LoggerFactory.getLogger(FocusServiceImpl.class);

}
