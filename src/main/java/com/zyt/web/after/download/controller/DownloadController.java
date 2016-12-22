package com.zyt.web.after.download.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.utils.FileUtils;

@Controller
@RequestMapping(value = "/download")
public class DownloadController extends BaseController {
	
	@RequestMapping("/template/{realName}/{ext}")
	public void downloadTemplate(HttpServletRequest request,  
            HttpServletResponse response,@PathVariable("realName") String realName,@PathVariable("ext") String realNameExt){
		String contentType = "application/octet-stream";
		String filename=realName+"."+realNameExt;
		FileUtils.download(request, response, filename, contentType,  
				filename);
	}
}
