package com.zyt.web.publics.utils;


import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

import java.io.File;

/**
 * 
 * @author LiuChuang
 * @description 媒体格式化工具类
 * @version 1.0
 * @date 2014-5-20 
 * @modify by YuJ on 2014-07-03 
 * Made some changes :
 * 1:Changed Class name FormatAudioUtils to FormatMediaUtils
 * 2:Created formatVideo Method
 */
public class FormatMediaUtils {
	
	/**
	 * 
	 *@Description: 把音频文件格式化
	 *@param source 需要格式化的音频文件
	 *@param target 格式化后的音频文件
	 *@param codec 音频编码格式
	 *@param format 音频所需格式
	 *@version: v1.0.0 
	 *@author: LiuChuang
	 * @throws EncoderException 
	 * @date: 2014-5-20上午11:18:30
	 */
	public static void formatAudio(File source,File target,String codec,String format) throws EncoderException {
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec(codec);
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat(format);
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		 try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			System.out.println("格式音频的时出现如下错误："+ e);
			throw e;
		} catch (InputFormatException e) {
			System.out.println("格式音频的时出现如下错误："+ e);
			throw e;
		} catch (EncoderException e) {
			System.out.println("格式音频的时出现如下错误："+ e);
			throw e;
		}
	}
	/**
	 * @Description 格式视频文件格式
	 * @param source 源文件（需要格式的文件）
	 * @param target 目标文件（格式后的文件）
	 * @param codec 视频格式
	 * @param format 格式编码
	 * @throws EncoderException
	 */
	public static void formatVideo(File source,File target,String codec,String format) throws EncoderException {
		VideoAttributes video = new VideoAttributes();
		video.setCodec(codec);
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat(format);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		 try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			System.out.println("格式视频的时出现如下错误："+ e);
			throw e;
		} catch (InputFormatException e) {
			System.out.println("格式视频的时出现如下错误："+ e);
			throw e;
		} catch (EncoderException e) {
			System.out.println("格式视频的时出现如下错误："+ e);
			throw e;
		}
	}

}
