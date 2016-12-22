package com.zyt.web.publics.module.sysmanager.bean;

import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 
 * @author LiuChuang
 * @description 用户实体
 * @version 1.0
 * @date 2014-3-11
 */
@Alias("User")
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 用户名
	 */
	@NotNull(message="用户名不能为空")
	private String userName;
	
	/**
	 * 密码
	 */
	@NotNull(message="密码不能为空")
	private String password;
	
	/**
	 * 是否锁定 false表示没有锁住
	 */
	private Boolean locked;
	
	/**
	 * 是否过期 false 表示没有过期
	 */
	private Boolean expire;
	
	/**
	 * 权限集合
	 */
	private Collection<? extends GrantedAuthority> authorities;
	
	/**
	 * -1：认证未通过，0：未认证，1：认证通过，2：待认证
	 */
	private Integer isAuthentication;
	
	/**
	 * 此处字段不能修改为包装Boolean类型
	 */
	private boolean credentialsNonExpired;
	
	/**
	 * 登录起点 1:app，2：website
	 */
	private String from;
	
	/**
	 * app端注册或登录成功后系统生成的用户token
	 */
	private String token;
	
	/**
	 * 用户类型（0：超级管理员、100：区域用户<只能web端登录>、200：机构用户<只能web端登录>、300：会员用户<只能App端登录>、400：咨询人员<负责处理咨询回复>、500：会计<只能App端登录>）
	 */
	private String type;
	
	/**
	 * 设备唯一标识码
	 */
	private String imie;
	
	/**
	 * 是否启用 注意 此处boolean不能修改为Boolean 包装类否则前端表单不能提交的值，mvc不会封装到Bean中
	 */
	private boolean enabled;
	
	/**
	 * 用户组
	 */
	private List<Group> groups;
	
	/**
	 * 用户角色
	 */
	private List<Role> roles;
	
	/**
	 * 用户扩展信息
	 */
	private ExtUser extUser;
	/**
	 * 是否能够发送通知（针对会员）
	 */
	private boolean hasSend;
	
	/**
	 * 第三方用户唯一值
	 */
	private String openId;

	public boolean isHasSend() {
		return hasSend;
	}

	public void setHasSend(boolean hasSend) {
		this.hasSend = hasSend;
	}

	/**
	 * ios设备Id
	 */
	private String iosToken;
	
	/**
	 * 
	 *@Description: 获取当前登录用户
	 *@return UserDetails 当前登录用户信息
	 *@version: v1.0.0
	 */
	public static UserDetails getInstance() {
		User userDetails =null;
		if (RequestContextHolder.getRequestAttributes() != null) {
		    Object obj=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER);
			if(obj!=null ){
				userDetails  = (User)obj;
			}
		    if(obj == null &&  SecurityContextHolder.getContext().getAuthentication() != null){
				userDetails =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER, userDetails);
			}
			return userDetails;
		}
		return null;
	}
	
	public User() {
		super();
	}



	public User(String id, String userName, String type, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Integer isAuthentication, String from, String token, 
			String avatar, String orgId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.locked = accountNonLocked;
		this.expire = accountNonExpired;
		this.authorities = authorities;
		this.isAuthentication = isAuthentication;
		this.credentialsNonExpired = credentialsNonExpired;
		this.from = from;
		this.token = token;
		this.type = type;
		if (extUser == null) {
			extUser = new ExtUser();
			extUser.setId(id);
			extUser.setAvatar(avatar);
			extUser.setOrgId(orgId);
		} else {
			extUser.setAvatar(avatar);
			extUser.setOrgId(orgId);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.isExpire() ;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean isExpire() {
		return expire;
	}

	public void setExpire(Boolean expire) {
		this.expire = expire;
	}

	public Integer getIsAuthentication() {
		return isAuthentication;
	}

	public void setIsAuthentication(Integer isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public ExtUser getExtUser() {
		return extUser;
	}

	public void setExtUser(ExtUser extUser) {
		this.extUser = extUser;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getIosToken() {
		return iosToken;
	}

	public void setIosToken(String iosToken) {
		this.iosToken = iosToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
