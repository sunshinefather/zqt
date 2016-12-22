package com.zyt.web.publics.utils.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author LiuChuang
 * @description 压缩图片工具类，把图片压缩到相应像素，并转换为JPEG格式
 * @version 1.0
 * @date 2014年12月1日
 */
public class ImageUtil {
	
	/**
	 * 
	 *@Description: 切割图片
	 *原理：将待裁剪图片宽高相除值和目标尺寸宽高相除值进行比较，如果前者较大，说明待裁剪图片相对于目标尺寸来说要宽出一块，
	 *现在已高为基准进行裁剪，即将原始图片的高度缩放到目标尺寸的高度。计算出原始图片高度和目标尺寸高度的比例，和原始图片宽度相乘，得到缩放后的宽度。
	 *此时的缩缩放后的高度就是目标尺寸的高度，但是宽度*肯定*会比目标尺寸宽度宽。将缩放后的多余的宽度分为两份右边和左边各裁去一份。 
	 *以宽为基准原理类似。
	 *@param srcImgFile 待切割图片路径
	 *@param destImgFile 切割后图片路径
	 *@param suffix 文件名后缀
	 *@param destImgW 所需宽度
	 *@param destImgH 所需高度
	 *@param scale 是否按等比例
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014年12月1日上午9:49:09
	 */
	public static boolean createThumb (File srcImgFile , File destImgFile,String suffix, int destImgW , int destImgH,boolean scale){
	    //原图片等比例缩小或放大之后的图片
	    int narrowImgW ;
	    int narrowImgH ;
	    //原图片大小
	    int srcImgW ;
	    int srcImgH ;
	    try {
	        BufferedImage bi = ImageIO.read(srcImgFile);
	        srcImgW = bi.getWidth();
	        srcImgH = bi.getHeight();
	        // 转换图片尺寸与目标尺寸比较 ， 如果转换图片较小，说明转换图片相对于目标图片来说高较小，需要以高为基准进行缩放。
	        if((float )srcImgW /srcImgH > (float)destImgW / destImgH){
	            narrowImgW = ( int)(((float )destImgH / (float)srcImgH)*srcImgW);
	            narrowImgH = destImgH;
	            
	            //按照原图以高为基准等比例缩放、或放大。这一步高为所需图片的高度，宽度肯定会比目标宽度宽。
	            int cutNarrowImgSize = (narrowImgW - destImgW)/2;
	            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH,BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = narrowImg.createGraphics();
	            g.drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH ), 0, 0,Color.WHITE, null);
	            g.dispose();
	            
	            if(!scale){
	            	//等比例缩放完成后宽度与目标尺寸宽度相比较 ， 将多余宽的部分分为两份 ，左边删除一部分
	            	Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT );
	            	CropImageFilter cropFilter = new CropImageFilter(cutNarrowImgSize, 0, narrowImgW-cutNarrowImgSize, narrowImgH);
	            	Image img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            	narrowImg = new BufferedImage( narrowImgW-cutNarrowImgSize, narrowImgH,BufferedImage.TYPE_INT_RGB );
	            	g = narrowImg.createGraphics();
	            	g.drawImage(img, 0, 0,Color.WHITE, null);
	            	g.dispose();
	            	
	            	//右边删除一部分
	            	image = narrowImg.getScaledInstance(narrowImgW-cutNarrowImgSize, narrowImgH, Image.SCALE_DEFAULT );
	            	cropFilter = new CropImageFilter(0, 0, narrowImgW-cutNarrowImgSize*2, narrowImgH);
	            	img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            	narrowImg = new BufferedImage( narrowImgW-cutNarrowImgSize*2, narrowImgH,BufferedImage.TYPE_INT_RGB );
	            	g = narrowImg.createGraphics();
	            	g.drawImage(img, 0, 0,Color.WHITE, null); // 绘制截取后的图，背景色设置为白色
	            	g.dispose();
	            }
	            
	            //输出为文件
	            return ImageIO. write(narrowImg, suffix, destImgFile);
	        }else{ //以宽度为基准
	            narrowImgW = destImgW;
	            narrowImgH = ( int) (((float )destImgW / (float)srcImgW)*srcImgH);
	            int cutNarrowImgSize = (narrowImgH - destImgH)/2;
	 
	            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH,BufferedImage.SCALE_DEFAULT);
	            Graphics2D g = narrowImg.createGraphics();
	            g.drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH ), 0, 0,Color.WHITE, null);
	            g.dispose();
	 
	           if(!scale){ //不按等比例压缩，宽高都进行压缩
	        	    Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT );
		            CropImageFilter cropFilter = new CropImageFilter(0, cutNarrowImgSize, narrowImgW, narrowImgH-cutNarrowImgSize);
		            Image img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
		            narrowImg = new BufferedImage( narrowImgW, narrowImgH-cutNarrowImgSize,BufferedImage.TYPE_INT_RGB);
		            g = narrowImg.createGraphics();
		            g.drawImage(img, 0, 0,Color.WHITE, null);
		            g.dispose();
		 
		            image = narrowImg.getScaledInstance(narrowImgW, narrowImgH-cutNarrowImgSize, Image.SCALE_DEFAULT);
		            cropFilter = new CropImageFilter(0, 0, narrowImgW, narrowImgH-cutNarrowImgSize*2);
		            img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
		            narrowImg = new BufferedImage( narrowImgW, narrowImgH-cutNarrowImgSize*2,BufferedImage.TYPE_INT_RGB);
		            g = narrowImg.createGraphics();
		            g.drawImage(img, 0, 0,Color.WHITE, null);
		            g.dispose();
	           }
	            
	            return ImageIO.write(narrowImg, suffix, destImgFile);
	        }
	    } catch (IOException e) {
	        return false;
	    }
	}
	
	public static void main(String []args){
	    System.out.println(createThumb(new File("F://logo.png"),new File("F://223.jpg"),"JPEG", 510, 510,true));
	}
}
