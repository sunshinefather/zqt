package com.zyt.web.after.api.controller;
 
import httl.util.StringUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zyt.web.after.api.service.MailListAPIService;
import com.zyt.web.after.api.service.UserAPIService;

@Controller
@RequestMapping(value = "/api/user")
public class ApiUserController extends ApiController{
   private static Logger logger = LoggerFactory.getLogger(ApiUserController.class);
   @Autowired
   private MailListAPIService mailListAPIService;
   @Autowired
   private  UserAPIService userAPIService;
   
       /**
        * 登录
        * signup(注册) signin(登入) signout(登出)
        * @Title: signUpNoOrg
        * @Description: TODO  
        * @param: @param request
        * @param: @return      
        * @return: String
        * @author: sunshine  
        * @throws
        */
	   @RequestMapping(value = "/signin",produces={"text/plain;charset=utf-8"})
	   @ResponseBody
	   public String signin(HttpServletRequest request) {
	           String userName = request.getParameter("userName");
	           String passWord = request.getParameter("passWord");
	           return userAPIService.signUp(userName, passWord);
	   }
       /**
        * 注销
        * @Title: signOut
        * @Description: TODO  
        * @param: @param request
        * @param: @return      
        * @return: String
        * @author: sunshine  
        * @throws
        */
	   @RequestMapping(value = "signout",produces={"text/plain;charset=utf-8"})
	   @ResponseBody
	   public String signOut(HttpServletRequest request) {
	           String token = request.getParameter("token");
	           return userAPIService.signOut(token);
	   }
	   
	   /**
	    * 
	    *@Description: 更改用户密码
	    *@param request
	    *@version: v1.0.0
	    *@author: YuJ
	    *@date: 2014年9月15日下午5:49:14
	    *Modification History:
	    *     Date		            Author		   Version		Description
	    * ---------------------------------------------------------*
	    * 2014-09-15	     LiuChuang		v1.0.0		 cleanup code
	    */
	   @RequestMapping(value = "modifyPassWord",produces={"text/plain;charset=utf-8"})
	   @ResponseBody
	   public String modifyPassWord(HttpServletRequest request) {
	           String token = request.getParameter("token");
	           String passWord = request.getParameter("passWord");
	           return userAPIService.modifyPassWord(token, passWord);
	   }
	   
	   /**
	    * 
	    *@Description: 修改密码，调用此方法需先验证手机号码
	    *@param newPassword 新密码
	    *@param userName 用户名（手机号码）
	    *@return
	    *@version: v1.0.0
	    *@author: Kevin
	    *@date: 2015年6月29日下午2:24:51
	    */
	   @RequestMapping(value = "modifyPassword",produces={"text/plain;charset=utf-8"})
	   public @ResponseBody String modifyPassword(@RequestParam(required = true)String newPassword,@RequestParam(required = true)String userName){
		   return userAPIService.modifyPassword(userName, newPassword);
	   }
   
   /**
	  * 
	  *@Description: 验证用户名
	  *@param request
	  *@return String
	  *@version: v1.0.0
	  *@author: YuJ
	  *@date: 2014年9月15日下午5:20:31
	  *Modification History:
	  *     Date		            Author		   Version		Description
	  * ---------------------------------------------------------*
	  * 2014-09-15	     LiuChuang		v1.0.0		 cleanup code
	  */
	   @RequestMapping(value = "validateUser",produces={"text/plain;charset=utf-8"})
	   @ResponseBody
	   public String validateUser(HttpServletRequest request) {
	           String userName = request.getParameter("userName");
	           return userAPIService.validateUser(userName);
	   }
   
	/**
	 * 获取通讯录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "mailList")
	@ResponseBody
	public void getMailList(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
		    response.setContentType("text/html");
			String token = request.getParameter("token");
			String orgId = request.getParameter("orgId");
			response.getWriter().print(mailListAPIService.getList(token,orgId));
		} catch (Exception e) {
			logger.error("调用API接口: mailList()时出现如下错误：" + e.getMessage());
		}
	}

	/**
	 * 获取用户的详细信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getMailListUserByUserId")
	public void getMailListUserById(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
		    response.setContentType("text/html");
			String userId = request.getParameter("userId");
			if (StringUtils.isNotBlank(userId))
				response.getWriter().print(mailListAPIService.getUserById(userId));
			else
				response.getWriter().print("{\"status\":0,\"msg\":\"参数错误!\"}");
		} catch (Exception e) {
			logger.error("调用API接口: getMailListUserByUserId()时出现如下错误：" + e.getMessage());
		}
	}
	/**
	 * 修改用户电话号码隐藏状态
	 * @param token 用户令牌
	 * @param avatar 用户头像地址
	 * @return json格式字符串
	 */
	@RequestMapping(value = "updateHideMobile",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateHideMobile(String token,String hideMobile) {
		try {
			return	mailListAPIService.updateHideMobile(token, hideMobile);
		} catch (Exception e) {
			logger.error("调用API接口: updateHideMobile()时出现如下错误：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 修改用户用户头像地址
	 * @param token 用户令牌
	 * @param avatar 用户头像地址
	 * @return json格式字符串
	 */
	@RequestMapping(value = "updateAvatar",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateAvatar(String token,String avatar,String avatarUrl) {
		try {
			return	mailListAPIService.updateAvatar(token, avatar,avatarUrl);
		} catch (Exception e) {
			logger.error("调用API接口: updateAvatar()时出现如下错误：" + e.getMessage());
		}
		return null;
	}
}