package com.zyt.web.publics.utils;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * @author Kevin
 * @description 条码工具类
 * @version 1.0
 * @date 2015年10月19日
 */
public class BarcodeUtils {
	private static final String CHARSET = "UTF-8";  
    private static final String FORMAT_NAME = "jpg";  
    private static final int BARCODE_WIDTH = 120;
    private static final int BARCODE_HEIGTH = 70;
    
    private static BufferedImage createImage(String content) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.CODE_128, BARCODE_WIDTH, BARCODE_HEIGTH, hints);  
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  
            } 
        }
        return image;  
    }
    
    public static void encode(String content,OutputStream output) throws Exception {  
        BufferedImage image = BarcodeUtils.createImage(content);  
        ImageIO.write(image,FORMAT_NAME,output);  
    }
    
}
