package com.baoliao.weixin.util;

import com.baoliao.weixin.Constants;
import com.baoliao.weixin.bean.AccessToken;
import com.baoliao.weixin.bean.User;
import com.baoliao.weixin.wechatpay.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

public class Utils {

    private static Logger log = LoggerFactory.getLogger(Utils.class);

    private static int socketTimeout = 10000;// 连接超时时间，默认10秒
    private static int connectTimeout = 30000;// 传输超时时间，默认30秒
    private static RequestConfig requestConfig;// 请求器的配置
    private static CloseableHttpClient httpClient;// HTTP请求器

    public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "D:\\tupian\\a.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String newFileName, String filePah, String format) {   //对字节数组字符串进行Base64解码并生成图片
        imgStr = imgStr.replace("data:image/jpeg;base64,", "");
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            String imgFilePath = filePah + newFileName + format;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String zxingCodeCreate(String content, String path, Integer size, String logoPath) {
        String fileName = "";
        try {
            //图片类型
            String imageType = "jpg";
            //获取二维码流的形式，写入到目录文件中
            BufferedImage image = getBufferedImage(content, size, logoPath);
            //获得随机数
            Random random = new Random();
            String currDateStr = Constants.DATA_FORMAT.sdf1.format(new Date());
            fileName = currDateStr + String.valueOf(random.nextInt(1000));
            //生成二维码存放文件
            log.info("保存二维码的路径:" + path + fileName + ".jpg");
            File file = new File(path + fileName + ".jpg");
            log.info("File对象:" + file);
            if (!file.exists()) {
                file.mkdirs();
            }
            ImageIO.write(image, imageType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName + ".jpg";
    }

    public static BufferedImage getBufferedImage(String content, Integer size, String logoPath) {
        if (size == null || size <= 0) {
            size = 250;
        }
        BufferedImage image = null;
        try {
            // 设置编码字符集
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //设置编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置容错率最高
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 0);
            // 1、生成二维码
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
//            bitMatrix = deleteWhite(bitMatrix);//删除白边
            // 2、获取二维码宽高
            int codeWidth = bitMatrix.getWidth();
            int codeHeight = bitMatrix.getHeight();
            // 3、将二维码放入缓冲流
            image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    // 4、循环将二维码内容定入图片
                    image.setRGB(i, j, bitMatrix.get(i, j) ? BLACK : WHITE);
                }
            }
            //判断是否写入logo图片
            if (logoPath != null && !"".equals(logoPath)) {
                File logoPic = new File(logoPath);
                if (logoPic.exists()) {
                    Graphics2D g = image.createGraphics();
                    BufferedImage logo = ImageIO.read(logoPic);
                    int widthLogo = logo.getWidth(null) > image.getWidth() * 2 / 10 ? (image.getWidth() * 2 / 10) : logo.getWidth(null);
                    int heightLogo = logo.getHeight(null) > image.getHeight() * 2 / 10 ? (image.getHeight() * 2 / 10) : logo.getHeight(null);
                    int x = (image.getWidth() - widthLogo) / 2;
                    int y = (image.getHeight() - heightLogo) / 2;
                    // 开始绘制图片
                    g.drawImage(logo, x, y, widthLogo, heightLogo, null);
                    g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
                    //边框宽度
                    g.setStroke(new BasicStroke(2));
                    //边框颜色
                    g.setColor(Color.WHITE);
                    g.drawRect(x, y, widthLogo, heightLogo);
                    g.dispose();
                    logo.flush();
                    image.flush();
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

    public static void bigImgAddSmallImgAndText(String bigImgPath
            , String smallImgPath, int sx, int sy
            , String price, int cx, int cy
            , String outPathWithFileName, int fontSize) throws IOException {
        //主图片的路径
        InputStream is = new FileInputStream(bigImgPath);
        //通过JPEG图象流创建JPEG数据流解码器
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        //解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        //得到画笔对象
        Graphics g = buffImg.getGraphics();

        //小图片的路径
        ImageIcon imgIcon = new ImageIcon(smallImgPath);
        //得到Image对象。
        Image img = imgIcon.getImage();
        //将小图片绘到大图片上,5,300 .表示你的小图片在大图片上的位置。
        g.drawImage(img, sx, sy, null);
        //设置颜色。
        g.setColor(Color.WHITE);

        //最后一个参数用来设置字体的大小
        if (price != null) {
            Font f = new Font("宋体", Font.PLAIN, fontSize);
            Color mycolor = Color.WHITE;//new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(f);
            g.drawString(price, cx, cy); //表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
        }

        g.dispose();
        OutputStream os = new FileOutputStream(outPathWithFileName);
        //创键编码器，用于编码内存中的图象数据。
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        is.close();
        os.close();
    }

    public static User getUserInfoByCode(HttpServletRequest request, String code) {
        log.info("通过网页授权获取用户信息。");
        String oauth2_token_url = Constants.URL.OAUTH2_ACCESS_TOKEN.replace("APPID", Constants.WECHAT_PARAMETER.APPID).replace("SECRET", Constants.WECHAT_PARAMETER.APPSECRET).replace("CODE", code);
        JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(oauth2_token_url, "GET", null);
        String openId = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String userinfourl = Constants.URL.OAUTH2_USERINFO_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
        jsonObject = WeixinIntefaceUtil.httpRequest(userinfourl, "GET", null);
        String nickName = jsonObject.getString("nickname");
        String sex = jsonObject.getString("sex");
        String language = jsonObject.getString("language");
        String city = jsonObject.getString("city");
        String province = jsonObject.getString("province");
        String country = jsonObject.getString("country");
        String headImgUrl = jsonObject.getString("headimgurl");

        User user = new User();
        user.setOpenId(openId);
        user.setNickName(nickName);
        user.setSex(Integer.parseInt(sex));
        user.setLanguage(language);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setHeadImgUrl(headImgUrl);
        request.getSession().setAttribute("user", user);
        return user;
    }

    public static User getUserInfoByOpenId(String openId) {
        log.info("通过openId获取用户信息。");
        AccessToken accessToken = WeixinIntefaceUtil.getAccessToken(Constants.WECHAT_PARAMETER.APPID, Constants.WECHAT_PARAMETER.APPSECRET);
        log.info("AccessToken==>" + accessToken);
        String access_token = accessToken.getToken();
        log.info("access_token==>" + access_token);
        String getUserInfoByOpenIdUrl = Constants.URL.GET_USERINFO_BY_OPENID_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
        JSONObject jsonObject = WeixinIntefaceUtil.httpRequest(getUserInfoByOpenIdUrl, "GET", null);
        log.info("jsonObject==>" + jsonObject.toString());
        String nickName = jsonObject.getString("nickname");
        String sex = jsonObject.getString("sex");
        String language = jsonObject.getString("language");
        String city = jsonObject.getString("city");
        String province = jsonObject.getString("province");
        String country = jsonObject.getString("country");
        String headImgUrl = jsonObject.getString("headimgurl");

        User user = new User();
        user.setOpenId(openId);
        user.setNickName(nickName);
        user.setSex(Integer.parseInt(sex));
        user.setLanguage(language);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setHeadImgUrl(headImgUrl);
        return user;
    }

    /**
     * 将图片写入到磁盘
     *
     * @param img      图片数据流
     * @param fileName 文件保存时的名称
     */
    public static void writeImageToDisk(byte[] img, String fileName, String path) {
        try {
            File file = new File(path + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static BufferedImage transferImgForRoundImgage(String url) {
        BufferedImage resultImg = null;
        try {
            if (StringUtils.isBlank(url)) {
                return null;
            }
            BufferedImage buffImg1 = ImageIO.read(new URL(url));
            resultImg = new BufferedImage(buffImg1.getWidth(), buffImg1.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resultImg.createGraphics();
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, buffImg1.getWidth(), buffImg1.getHeight());
            // 使用 setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            resultImg = g.getDeviceConfiguration().createCompatibleImage(buffImg1.getWidth(), buffImg1.getHeight(),
                    Transparency.TRANSLUCENT);
            //g.fill(new Rectangle(buffImg2.getWidth(), buffImg2.getHeight()));
            g = resultImg.createGraphics();
            // 使用 setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setClip(shape);
            g.drawImage(buffImg1, 0, 0, null);
            g.dispose();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return resultImg;
    }

    /**
     * 数字字符串转为两位数
     *
     * @param st
     * @return
     */
    public static String conversion2Mumber(String st) {
        Double d = Double.parseDouble(st);
        DecimalFormat df = new DecimalFormat("0.00");//格式化
        return df.format(d);
    }

    /**
     * 图片添加水印
     *
     * @param srcImgPath       需要添加水印的图片的路径
     * @param outImgPath       添加水印后图片输出路径
     * @param fontType         水印文字的字体
     * @param fontStyle        水印文字的字体份风格
     * @param markContentColor 水印文字的颜色
     * @param fontSize         水印的文字的大小
     * @param waterMarkContent 水印的文字内容
     */
    public static void waterPress(String srcImgPath, String outImgPath, String fontType, int fontStyle, Color markContentColor, int fontSize, String waterMarkContent, int y) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);
            Image srcImg = null;
            if (srcImgFile.exists() && srcImgFile.isFile() && srcImgFile.canRead()) {
                srcImg = ImageIO.read(srcImgFile);
            }
            // 宽、高
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            Font font = new Font(fontType, fontStyle, fontSize);
            //设置水印颜色
            g.setColor(markContentColor);
            g.setFont(font);
            // 抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int fontLength = getWatermarkLength(waterMarkContent, g);
            // 实际生成的水印文字，实际文字行数
            Double textLineCount = Math.ceil(Integer.valueOf(fontLength).doubleValue() / Integer.valueOf(srcImgWidth).doubleValue());
            // 实际所有的水印文字的高度
            int textHeight = textLineCount.intValue() * fontSize;
            // 相对与X的起始的位置
            int originX = 0;
            // 相对与Y的起始的位置
            int originY = 0;
            // 实际文字大于1行，则x则为默认起始0，
            if (1 == textLineCount.intValue()) {
                // 实际文字行数是1，1/2个图片高度，减去1/2个字符高度
//                originY = srcImgHeight / 2 - fontSize / 2;
                originY = y;
                // 实际文字行数是1，计算x的居中的起始位置
                originX = (srcImgWidth - fontLength) / 2;
            } else {
                originY = y;
                // 实际文字行数大于1，1/2个图片高度减去文字行数所需的高度
//                originY = (srcImgHeight - textHeight) / 2;
            }
            log.info("水印文字总长度:" + fontLength + ",图片宽度:" + srcImgWidth + ",字符个数:" + waterMarkContent.length());
            //文字叠加,自动换行叠加
            int tempX = originX;
            int tempY = originY;
            int tempCharLen = 0;//单字符长度
            int tempLineLen = 0;//单行字符总长度临时计算
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < waterMarkContent.length(); i++) {
                char tempChar = waterMarkContent.charAt(i);
                tempCharLen = getCharLen(tempChar, g);
                if (tempLineLen >= srcImgWidth) {
                    // 绘制前一行
                    g.drawString(stringBuffer.toString(), tempX, tempY);
                    //清空内容,重新追加
                    stringBuffer.delete(0, stringBuffer.length());
                    //文字长度已经满一行,Y的位置加1字符高度
                    tempY = tempY + fontSize;
                    tempLineLen = 0;
                }
                //追加字符
                stringBuffer.append(tempChar);
                tempLineLen += tempCharLen;
            }
            //最后叠加余下的文字
            g.drawString(stringBuffer.toString(), tempX, tempY);
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "png", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCharLen(char c, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charWidth(c);
    }

    /**
     * 获取水印文字总长度
     *
     * @paramwaterMarkContent水印的文字
     * @paramg
     * @return水印文字总长度
     */
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }


    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        } catch (Exception ex) {
        }
        return output;
    }

    public static String getSignCode(Map<String, String> map, String keyValue) {
        String result = "";
        try {
//            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(key + "=" + val + "&");
                    }
                }

            }
            sb.append("key=" + keyValue);
            result = sb.toString();

            //进行MD5加密
            result = WXPayUtil.MD5(result).toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static String doRefund(String url, String xmlData, String mchId) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(Constants.WECHAT_PARAMETER.certPath));//P12文件目录  写证书的项目路径
        try {
            keyStore.load(instream, mchId.toCharArray());//这里写密码..默认是你的MCHID 证书密码
        } finally {
            instream.close();
        }


        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mchId.toCharArray())//这里也是写密码的
                .build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(xmlData, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                String returnMessage = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return returnMessage; //返回后自己解析结果
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }


    public static void main(String[] args) {
        //首先生成二维码
//        String fileName = zxingCodeCreate("http://k5eqmb.natappfree.cc/product/detailInfo?id=7&price=1&openId=ohDAp1PJ7rxxLGZIoKbN1T2UllIo", "D:/CCQ/", 250, "D:\\CCQ\\ideaWork\\baoliao\\src\\main\\resources\\static\\img\\logo.png");
        String fileName = Utils.zxingCodeCreate("https://open.weixin.qq.com/connect/oauth2/authorize?appid=12324234444424&redirect_uri=wwwww..sfsfs.ccc/product/detailInfo%3Fid%3D7%26price%3D88%26openId%3DohDAp1PJ7rxxLGZIoKbN1T2UllIo&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect", "D:/CCQ/", 250, "D:\\CCQ\\ideaWork\\baoliao\\src\\main\\resources\\static\\img\\logo.png");
// String st = "䵺";
//        log.info(st);
        try {
            // 将二维码印到模板图片，并添加价格
            bigImgAddSmallImgAndText("D:\\CCQ\\ideaWork\\baoliao\\src\\main\\resources\\static\\img\\muban1.jpg", "D:\\CCQ\\" + fileName, 250, 300, "免费", 600, 650, "D:\\CCQ\\" + fileName, 45);
            // 下载图片到本地
            byte[] btImg = getImageFromNetByUrl("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132");
            if (null != btImg && btImg.length > 0) {
                log.info("读取到：" + btImg.length + " 字节");
                String headImgName = "headImg.jpg";
                writeImageToDisk(btImg, headImgName, "D:\\CCQ\\");
                // 变圆

                BufferedImage bi1 = ImageIO.read(new File("D://CCQ//headImg.jpg"));
                bi1 = transferImgForRoundImgage("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132");
// 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
                BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                        BufferedImage.TYPE_INT_RGB);

                Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
                        .getHeight());

                Graphics2D g2 = bi2.createGraphics();
                // g2.setBackground(Color.WHITE);

//                g2.setClip(shape);
//                g2.setBackground(Color.WHITE);
//                g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
//                g2.setColor(new Color(255,0,0));
//                g2.setStroke(new BasicStroke(1));
                bi2 = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
                // 使用 setRenderingHint 设置抗锯齿
                g2.dispose();
                g2 = bi2.createGraphics();
                g2.drawImage(bi1, 0, 0, null);
                g2.dispose();

                try {
                    ImageIO.write(bi2, "jpg", new File("D://CCQ//headImg.jpg"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //将头像 和昵称加载到图片
                bigImgAddSmallImgAndText("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + headImgName, 100, 600, "寻梦依然", 250, 650, "D:\\CCQ\\" + fileName, 22);
// 添加文字和标题
                String fontType = "宋体";
                int fontStyle = Font.BOLD;
                int fontSize = 30;
                String font = "测试标题";
                /* String font = "印效果测水印效果整水印效果 ";*/
                waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.WHITE, 35, font, 150);
                String font1 = "测试简介内容测试简介内容测试简介内容测试简介内容测试简介内容阿斯顿撒大苏打撒旦大撒大撒大苏打啊实打实大苏打";
                if (font1.length() > 16) {
                    String font1_sub1 = font1.substring(16, font1.length() - 1);
                    waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1.substring(0, 16), 220);
                    if (font1_sub1.length() > 16) {
                        String font1_sub2 = font1.substring(16, font1.length() - 1);
                        waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1_sub2.substring(0, 16), 260);
                        if (font1_sub2.length() > 16) {
                            String font1_sub3 = font1.substring(16, font1.length() - 1);
                            waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1_sub3.substring(0, 16), 260);

                        } else {
                            waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1_sub2, 260);
                        }
                    } else {
                        waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1_sub1, 260);
                    }
                } else {
                    waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.blue, fontSize, font1, 220);
                }

                String font2 = "长按扫码 立即获取";
                waterPress("D:\\CCQ\\" + fileName, "D:\\CCQ\\" + fileName, fontType, fontStyle, Color.gray, 18, font2, 580);

            } else {
                log.info("没有从该连接获得内容");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* String url = "http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfAA1AJAgRCFthEdAvqzMSut19A09ibzBVv5lkjdia643BGmXrLKeZZJ5sXptUyjrHyILcJHcax58A/132";
        byte[] btImg = getImageFromNetByUrl(url);
        if(null != btImg && btImg.length > 0){
            log.info("读取到：" + btImg.length + " 字节");
            String fileName = "test.jpg";
            writeImageToDisk(btImg, fileName);
        }else{
            log.info("没有从该连接获得内容");
        }*/
        /*try {
            //主图片的路径
            InputStream is = new FileInputStream("D:\\\\CCQ\\\\ideaWork\\\\baoliao\\\\src\\\\main\\\\resources\\\\static\\\\img\\\\muban1.jpg");
            //通过JPEG图象流创建JPEG数据流解码器
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
            BufferedImage image = jpegDecoder.decodeAsBufferedImage();
            String text = "文字居中";
            int width = 500;
            int height = 400;
            // 创建BufferedImage对象
//            BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            // 获取Graphics2D
            Graphics2D g2d = image.createGraphics();
            // 画图
            g2d.setBackground(new Color(255,255,255));
            //g2d.setPaint(new Color(0,0,0));
            g2d.setColor(Color.red);
            g2d.clearRect(0, 0, width, height);
            Font font=new Font("宋体",Font.PLAIN,64);
            g2d.setFont(font);
            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 计算文字长度，计算居中的x点坐标
            FontMetrics fm = g2d.getFontMetrics(font);
            int textWidth = fm.stringWidth(text);
            int widthX = (width - textWidth) / 2;
            // 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g2d.drawString(text,widthX,100);
            // 释放对象
            g2d.dispose();
            // 保存文件
            ImageIO.write(image, "jpg", new File("D:/test.jpg"));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }*/
    }
}
