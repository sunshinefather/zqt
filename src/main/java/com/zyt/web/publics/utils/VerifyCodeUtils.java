package com.zyt.web.publics.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author maliang 验证码生成器
 */
public class VerifyCodeUtils {

	public static final int TYPE_NUM_ONLY = 0;
	public static final int TYPE_LETTER_ONLY = 1;
	public static final int TYPE_ALL_MIXED = 2;
	public static final int TYPE_NUM_UPPER = 3;
	public static final int TYPE_NUM_LOWER = 4;
	public static final int TYPE_UPPER_ONLY = 5;
	public static final int TYPE_LOWER_ONLY = 6;

	public static String generateTextCode(int type, int length, String exChars) {
		if (length <= 0) {
			return "";
		}
		StringBuffer code = new StringBuffer();
		int i = 0;
		Random r = new Random();

		switch (type) {
		case 0:
			while (i < length) {
				int t = r.nextInt(10);
				if ((exChars == null) || (exChars.indexOf(t) < 0)) {
					code.append(t);
					i++;
				}
			}
			break;
		case 1:
			while (i < length) {
				int t = r.nextInt(123);
				if (((t < 97) && ((t < 65) || (t > 90)))
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}

			break;
		case 2:
			while (i < length) {
				int t = r.nextInt(123);
				if (((t < 97) && ((t < 65) || (t > 90)) && ((t < 48) || (t > 57)))
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}

			break;
		case 3:
			while (i < length) {
				int t = r.nextInt(91);
				if (((t < 65) && ((t < 48) || (t > 57)))
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}

			break;
		case 4:
			while (i < length) {
				int t = r.nextInt(123);
				if (((t < 97) && ((t < 48) || (t > 57)))
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}
			break;
		case 5:
			while (i < length) {
				int t = r.nextInt(91);
				if ((t < 65)
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}

			break;
		case 6:
			while (i < length) {
				int t = r.nextInt(123);
				if ((t < 97)
						|| ((exChars != null) && (exChars.indexOf((char) t) >= 0)))
					continue;
				code.append((char) t);
				i++;
			}

		}

		return code.toString();
	}

	public static BufferedImage generateImageCode(String textCode, int width,
			int height, int interLine, boolean randomLocation, Color backColor,
			Color foreColor, Color lineColor) {
		BufferedImage bim = new BufferedImage(width, height, 1);
		Graphics g = bim.getGraphics();
		g.setColor(backColor == null ? getRandomColor() : backColor);
		g.fillRect(0, 0, width, height);

		Random r = new Random();
		if (interLine > 0) {
			int x = 0;
			int y = 0;
			int x1 = width;
			int y1 = 0;
			for (int i = 0; i < interLine; i++) {
				g.setColor(lineColor == null ? getRandomColor() : lineColor);
				y = r.nextInt(height);
				y1 = r.nextInt(height);

				g.drawLine(x, y, x1, y1);
			}

		}

		int fsize = (int) (height * 0.8D);
		int fx = 0;
		int fy = fsize;

		g.setFont(new Font("Dialog", 0, fsize));
		for (int i = 0; i < textCode.length(); i++) {
			fy = randomLocation ? (int) ((Math.random() * 0.3D + 0.6D) * height)
					: fy;
			g.setColor(foreColor == null ? getRandomColor() : foreColor);
			g.drawString(textCode.charAt(i) + "", fx, fy);
			fx = (int) (fx + width / textCode.length()
					* (Math.random() * 0.3D + 0.8D));
		}

		g.dispose();

		return bim;
	}

	private static Color getRandomColor() {
		Random r = new Random();
		Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		return c;
	}
}
