package com.zyt.web.publics.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

public class AESUtils {
	
	/** 算法名称 */  
    private static final String KEY_ALGORITHM = "AES";  
    
    /** 算法名称/加密模式/填充方式 */  
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";  
  
    /**密钥 */  
    public static final String SEC_KEY = "@^_^123aBcZ*&#!=";  
  
    /** 
     * 生成随机密钥<br/> 
     * 安全性较高，但密钥需要保存 
     *  
     * @param key 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */  
    @Deprecated  
    public static Key toRandomKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {  
        // 返回生成指定算法的秘密密钥的 KeyGenerator对象  
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);  
        // 初始化此密钥生成器，使其具有确定的密钥大小（AES 要求密钥长度为 128）  
        kgen.init(128, new SecureRandom(key.getBytes("UTF-8")));  
        // 生成一个密钥  
        SecretKey secretKey = kgen.generateKey();  
        // 构造密钥  
        byte[] enCodeFormat = secretKey.getEncoded();  
        return new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);  
    }  
  
    /** 
     * 生成一个固定密钥 
     *  
     * @param password 长度必须是16的倍数 但是JDK默认只支持16位数的。
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    private static Key toKey(String password) throws UnsupportedEncodingException {  
        return new SecretKeySpec(validLength(password).getBytes("UTF-8"), KEY_ALGORITHM);  
    }  
    
    /**
     * 
     *@Description: 校验key的长度，由于JDK默认支持16的，所以此处只使用16
     *@param password
     *@return String
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014年8月15日下午2:21:14
     */
    private static String validLength(String password){
    	if(password.length()< 16){
    		Integer l = password.length()%16;
    		return password+SEC_KEY.substring(0, l);
    	}else if(password.length() > 16){
    		return password.substring(0,16);
    	}
    	return password;
    }
  
    /** 
     * 将二进制转换成十六进制 
     *  
     * @param buf 
     * @return 
     */  
    private static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 将十六进制转换为二进制 
     *  
     * @param hexStr 
     * @return 
     */  
    private static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
            return null;  
        byte[] result = new byte[hexStr.length() / 2];  
        for (int i = 0; i < hexStr.length() / 2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);  
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);  
            result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
    }  
  
    /** 
     * 加密 
     *  
     * @param content 
     *            需要加密的内容 
     * @param password 
     *            加密密码 
     * @return 
     * @throws Exception 
     */  
    public static String encrypt(String content, String password) {  
        if (StringUtils.isBlank(content)) {  
            return "";  
        }  
        try {  
            // 生成密钥  
            Key key = toKey(password);  
            // 将String转换为二进制  
            byte[] byteContent = content.getBytes("UTF-8");  
            // 创建密码器  
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);  
            // 初始化为加密模式  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            // 执行加密加密  
            byte[] result = cipher.doFinal(byteContent);  
  
            return parseByte2HexStr(result);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
  
    /** 
     * 解密 
     *  
     * @param content 
     *            待解密内容 
     * @param password 
     *            解密密钥 
     * @return 
     * @throws Exception 
     */  
    public static String decrypt(String content, String password) {  
        if (StringUtils.isBlank(content)) {  
            return "";  
        }  
  
        try {  
            // 将十六进制转换为二进制  
            byte[] contentHex = parseHexStr2Byte(content);  
  
            // 生成密钥  
            Key key = toKey(password);  
            // 创建密码器  
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);  
            // 初始化解码模式  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            // 解密  
            byte[] result = cipher.doFinal(contentHex);  
  
            return new String(result, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
  
    public static void main(String[] args) throws Exception {  
        String content = "root";  
        
        System.out.println("加密前：" + content);  
        String encryptResultStr = encrypt(content, "mysql.jdbc.password"+SEC_KEY);  
        
        System.out.println("加密后：" + encryptResultStr);  
        System.out.println("解密后：" + decrypt(encryptResultStr, "mysql.jdbc.password"+SEC_KEY));  
    }  
}
