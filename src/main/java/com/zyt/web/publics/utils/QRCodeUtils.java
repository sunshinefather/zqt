package com.zyt.web.publics.utils;

import httl.util.StringUtils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * @author LiuChuang
 * @description 二维码生成工具
 * @version 1.0
 * @date 2014-2-26
 */
public class QRCodeUtils {
	private static final String CHARSET = "UTF-8";  
	
    private static final String FORMAT_NAME = "jpg";  
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 300;  
    // LOGO宽度  
    private static final int WIDTH = 60;  
    // LOGO高度  
    private static final int HEIGHT = 60;  
  
    private static BufferedImage createImage(String content, String imgPath,  
            boolean needCompress) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  
            } 
        }
        if (StringUtils.isBlank(imgPath))return image;
        // 插入图片
        QRCodeUtils.insertImage(image, imgPath, needCompress);
        return image;  
    }  
  
    
    /**
     * 
     *@Description: 写入LOGO
     *@param source 二维码图片
     *@param imgPath LOGO图片地址
     *@param needCompress 是否压缩
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:39:07
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    private static void insertImage(BufferedImage source, String imgPath,  
            boolean needCompress) throws Exception {  
        File file = new File(imgPath);  
        if (!file.exists())
           return;
        Image src = ImageIO.read(new File(imgPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > WIDTH) {  
                width = WIDTH;  
            }  
            if (height > HEIGHT) {  
                height = HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height,  
                    Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (QRCODE_SIZE - width) / 2;  
        int y = (QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }  
  
    /**
     * 
     *@Description: 生成二维码(内嵌LOGO) 
     *@param content 内容 
     *@param imgPath LOGO地址 
     *@param destPath 存放目录 
     *@param needCompress 是否压缩LOGO 
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:40:12
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static void encode(String content, String imgPath, String destPath,String fileName, boolean needCompress) throws Exception {  
    	mkdirs(destPath);
    	if(StringUtils.isBlank(fileName))
    		fileName = FileUtils.createUniqueName("",FORMAT_NAME);
    	String fileRealPath = destPath+File.separator+fileName;
        BufferedImage image = QRCodeUtils.createImage(content, imgPath,needCompress);
        ImageIO.write(image, FORMAT_NAME, new File(fileRealPath));
    }
  
    /**
     * 
     *@Description: 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常) 
     *@param destPath 存放目录
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:41:00
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static void mkdirs(String destPath) {  
        File file =new File(destPath);      
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
  
    
    /**
     * 
     *@Description: 生成二维码(内嵌LOGO) 
     *@param content 内容
     *@param imgPath LOGO地址 
     *@param destPath 存储地址
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:41:38
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static void encode(String content, String imgPath, String destPath)  
            throws Exception {  
        QRCodeUtils.encode(content, imgPath, destPath,null, false);  
    }  
  
    /**
     * 
     *@Description: 生成二维码
     *@param content 内容
     *@param destPath 存储地址
     *@param needCompress 是否压缩LOGO 
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:42:08
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static void encode(String content, String destPath,  
            boolean needCompress) throws Exception {  
        QRCodeUtils.encode(content, null, destPath,null, needCompress);  
    }  
  
  /**
   * 
   *@Description: 生成二维码
   *@param content 内容
   *@param destPath 存储地址
   *@throws Exception
   *@version: v1.0.0
   *@author: LiuChuang
   *@date: 2014-2-26上午10:42:38
   * 
   * Modification History:
   *     Date		      Author		   Version		Description
   * ---------------------------------------------------------*
   * 2014-2-26	     LiuChuang		v1.0.0		   新建
   */
    public static void encode(String content, String destPath) throws Exception {  
        QRCodeUtils.encode(content, null, destPath,null, false);  
    }  
  
    /**
     * 
     *@Description: 生成二维码
     *@param content 内容
     *@param imgPath 图片路径
     *@param output 输出流
     *@param needCompress 是否压缩
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:43:06
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static void encode(String content, String imgPath,  
            OutputStream output, boolean needCompress) throws Exception {  
        BufferedImage image = QRCodeUtils.createImage(content, imgPath, needCompress);  
        ImageIO.write(image, FORMAT_NAME, output);  
    }  
  
   /**
    * 
    *@Description: 生成二维码
    *@param content 内容
    *@param output 输出流
    *@throws Exception 
    *@version: v1.0.0
    *@author: LiuChuang
    *@date: 2014-2-26上午10:44:34
    * 
    * Modification History:
    *     Date		      Author		   Version		Description
    * ---------------------------------------------------------*
    * 2014-2-26	     LiuChuang		v1.0.0		   新建
    */
    public static void encode(String content, OutputStream output)  
            throws Exception {  
        QRCodeUtils.encode(content, null, output, false);  
    }  
  
    /**
     * 
     *@Description: 解析二维码
     *@param file 二维码图片
     *@return String
     *@throws Exception
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-26上午10:44:08
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-26	     LiuChuang		v1.0.0		   新建
     */
    public static String decode(File file) throws Exception {  
        BufferedImage image;  
        image = ImageIO.read(file);  
        if (image == null) {  
            return null;  
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(  
                image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        Result result;  
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);  
        result = new MultiFormatReader().decode(bitmap, hints);  
        String resultStr = result.getText();  
        return resultStr;  
    }  
  
   /**
    * 
    *@Description: 解析二维码
    *@param path 二维码地址
    *@return String
    *@version: v1.0.0
    *@author: LiuChuang
    *@date: 2014-2-26上午10:43:23
    * 
    * Modification History:
    *     Date		      Author		   Version		Description
    * ---------------------------------------------------------*
    * 2014-2-26	     LiuChuang		v1.0.0		   新建
    */
    public static String decode(String path) throws Exception {  
        return QRCodeUtils.decode(new File(path));  
    } 
    
    public static void main(String[] args) throws Exception {
    	String content = "http://www.weedu.cn/uploads/apps/zejiao.apk";
		QRCodeUtils.encode(content,"E:/Document/02设计文档/APP_Ui/Iocn/Ziocn.png", "d:/",null, true);
	}
}
