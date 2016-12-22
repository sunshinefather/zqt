package com.zyt.web.publics.servlet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyt.web.publics.utils.VerifyCodeUtils;

public class VerifyCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 验证码图片生成
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("image/jpeg");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setCharacterEncoding("utf-8");
        String codeStr = VerifyCodeUtils.generateTextCode(4, 4, null);
        // 添加到session
        BufferedImage bim = VerifyCodeUtils.generateImageCode(codeStr, 80, 30, 3, true, Color.WHITE, Color.BLACK, null);
        req.getSession().setAttribute("validateCode", codeStr);
        ImageIO.write(bim, "JPEG", resp.getOutputStream());
    }

}
