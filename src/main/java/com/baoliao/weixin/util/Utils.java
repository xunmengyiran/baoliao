package com.baoliao.weixin.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

public class Utils {
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

    public static boolean zxingCodeCreate(String content, String path, Integer size, String logoPath) {
        try {
            //图片类型
            String imageType = "jpg";
            //获取二维码流的形式，写入到目录文件中
            BufferedImage image = getBufferedImage(content, size, logoPath);
            //获得随机数
            Random random = new Random();
            //生成二维码存放文件
            File file = new File(path + random.nextInt(1000) + ".jpg");
            if (!file.exists()) {
                file.mkdirs();
            }
            ImageIO.write(image, imageType, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 1);
            // 1、生成二维码
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
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

    public static void main(String[] args) {
        System.out.println(zxingCodeCreate("1234567", "D:/CCQ/", 500, "D:\\CCQ\\ideaWork\\baoliao\\src\\main\\resources\\static\\img\\logo.png"));
    }
}
