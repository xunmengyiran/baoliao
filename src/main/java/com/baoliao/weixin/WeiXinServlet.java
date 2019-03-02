package com.baoliao.weixin;

import com.baoliao.weixin.dao.UserDao;
import com.baoliao.weixin.util.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WeiXinServlet", urlPatterns = "/WeiXinServlet")
public class WeiXinServlet extends HttpServlet {
    protected static final Log log = LogFactory.getLog(WeiXinServlet.class);
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = WeiXinService.processRequest(request,response, userDao);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        log.info("-------------开始校验签名！-----------------------");
        // 接收微信服务器传递的四个参数
        //微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 进行排序并加密
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            log.info("-------------签名校验通过:" + echostr + "----------------");
        } else {
            out.print(echostr);
            log.info("-------------签名校验失败----------------");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = this.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userDao = (UserDao) ctx.getBean("userDao");
    }
}
