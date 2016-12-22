package com.zyt.web.publics.cluster;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @ClassName:  DistributedHttpServletRequest   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2016年11月10日 下午4:34:16
 */
public class DistributedHttpServletRequest extends HttpServletRequestWrapper {
	
	private static final Logger logger = LoggerFactory.getLogger(DistributedHttpServletRequest.class);
	
    private final static String SESSION_ID_KEY = "JSESSIONID";
    private DistributedSession session;
    private HttpServletResponse response;
    private HttpServletRequest request;
    
	public DistributedHttpServletRequest(HttpServletRequest request,HttpServletResponse response,ServletContext context) {
		super(request);
        this.response = response;
        this.request=request;
	}
	
	@Override
    public HttpSession getSession(boolean isCreate) {
            String sessionId = null;
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(SESSION_ID_KEY.equals(cookie.getName())){
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }
            if(StringUtils.isEmpty(sessionId)){
                sessionId = UUID.randomUUID().toString();
                Cookie cookie = new Cookie(SESSION_ID_KEY, sessionId);
                this.response.addCookie(cookie);
            }
            int maxInactiveInterval = super.getSession(true).getMaxInactiveInterval();
            try {
				this.session = new DistributedSession(this.request,sessionId,maxInactiveInterval);
			} catch (Exception e) {
				logger.error("@sunshine:启用分布式session失败",e);
				return this.request.getSession(isCreate);
			}
            return this.session;
    }
    
    @Override
    public HttpSession getSession() {
        return getSession(false);
    }
}