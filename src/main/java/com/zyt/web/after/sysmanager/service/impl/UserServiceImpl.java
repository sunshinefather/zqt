package com.zyt.web.after.sysmanager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.hospital.dao.HospitalDao;
import com.zyt.web.publics.module.sysmanager.bean.ExtUser;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.module.sysmanager.dao.UserDao;
import com.zyt.web.publics.utils.UUIDUtils;

import httl.util.StringUtils;
 
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Override
	public User findUserByName(String userName,Boolean app) {
		return userDao.findUserByName(userName,app);
	}
	
	@Override
	public Integer save(User user) {
		Integer rows = this.validateUser(user.getUserName());
		if (rows <= 0) {
			if(StringUtils.isBlank(user.getId()))//没有ID时再创建
				user.setId(UUIDUtils.getUUID());
			user.setEnabled(true);
			user.setLocked(false);
			user.setExpire(false);
			
			if(user.getExtUser() == null){
				ExtUser extUser = new ExtUser();
				extUser.setId(user.getId());
				user.setExtUser(extUser);
			}
			
			user.getExtUser().setId(user.getId());
			user.getExtUser().setRegisterTime(new Date());
			user.getExtUser().setId(user.getId());
			user.getExtUser().setState(false);
			
			user.getExtUser().setRegisterTime(new Date());
			user.getExtUser().setId(user.getId());
			user.getExtUser().setState(false);
			this.saveUserGroupLink(user);
			userDao.save(user);
			rows = userDao.saveExt(user);
		} else {
			rows = 100;
		}
		return rows;
	}
	
	@Override
	@Transactional
	public Integer delete(User user) {
			userDao.deleteUserGroupLink(user);
			userDao.deleteExt(user);
			Integer row = userDao.delete(user);
			return row;
	}

	@Override
	@Transactional
	public Integer delete(String userIds) {
		Integer rows = 0;
		try {
			String[] ids = userIds.split(",");
			if (ids.length > 0) {
				User user = null;
				for (String id : ids) {
					user = new User();
					user.setId(id);
					userDao.deleteUserGroupLink(user);
					userDao.deleteExt(user);
					userDao.delete(user);
					rows += 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return rows;
		}
			return rows;
	}
	
	@Override
	public Integer update(User user) {
		this.updateExt(user);
		this.saveUserGroupLink(user);
		return userDao.update(user);
	}

	@Override
	public List<User> findMemberList(PaginationAble paginationAble,User user) {
		loadAuth(paginationAble.getWhereParameters(),user);
		return userDao.findMemberList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}
	
	
	@Override
	public List<User> findList(PaginationAble paginationAble,User user) {
		loadAuth(paginationAble.getWhereParameters(),user);
		return userDao.findList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}
	
	public List<User> findAllList(Map<String,Object>param,User user){
		if(param == null)param = new HashMap<String,Object>();
		loadAuth(param,user);
		return userDao.findAllList(param);
	}
	
	@Override
	public Integer transform(Integer state,String ...ids) {
		return userDao.transform(state,ids);
	}

	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}
	@Override
	public Integer deleteExt(User user) {
		return userDao.deleteExt(user);
	}
	@Override
	public Integer saveUserGroupLink(User user) {
		this.deleteUserGroupLink(user);
		if(user.getGroups() != null && user.getGroups().size() > 0)
			return userDao.saveUserGroupLink(user);
		return 0;
	}
	@Override
	public Integer updateExt(User user) {
		if(user.getExtUser() != null)
			return userDao.updateExt(user);
		return 0;
	}
	@Override
	public Integer deleteUserGroupLink(User user) {
		return userDao.deleteUserGroupLink(user);
	}

	@Override
	public User findUserByToken(String token) {
		return userDao.findUserByToken(token);
	}
	
	@Override
	public Boolean updateByToken(String id) {
		return userDao.updateByToken(id) > 0 ? true : false;
	}

	@Override
	public User findUserByNameAndPassWord(String userName, String passWord) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("passWord", passWord);
		return userDao.findUserByNameAndPassWord(params);
	}
	
	@Override
	public List<User> findUserByAll(String[] orgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgIds", orgIds);
		return userDao.findUserByAll(map);
	}
	
	@Override
	public String getFromByPublisher(String publisher) {
		String from = null;
		if (StringUtils.isNotBlank(publisher)) {
			User user = userDao.findUserById(publisher);
			if (user != null) {
				if ("0".equals(user.getType())) {//系统管理员
					from = "系统管理员";
				} else if ("100".equals(user.getType())) {//区域用户
					
				} else if ("200".equals(user.getType())) {//机构用户
					if (StringUtils.isNotBlank(user.getExtUser().getOrgId())) {
						Hospital org = hospitalDao.getById(user.getExtUser().getOrgId());
						if (org != null)
							from = org.getHospitalName();
					}
				}
			}
		}
		return from;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> findUserByOrgAndName(Map map) {
		return userDao.findUserByOrgAndName(map);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ExtUser> findUserByIds(Map map) {
		return userDao.findUserByIds(map);
	}
	@Override
	public List<ExtUser> findUserByOrgId(PaginationAble paginationAble) {
		return userDao.findUserByOrgId(paginationAble,  new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public Integer changePassword(String userId, String oldPwd, String newPwd) {
		return userDao.changePassword(userId, oldPwd, newPwd);
	}
	@Override
	public List<User> findUserByOrgIdNoPage(Map<String, Object> map) {
		return userDao.findUserByOrgIdNoPage(map);
	}
	@Override
	public List<ExtUser> findUserByOrgIds(Map<String, Object> map) {
		return userDao.findUserByOrgIds(map);
	}

	@Override
	public Map<String, Object> loadAuth(Map<String, Object> param, User user) {
		Assert.notNull(user,"用户不能为空");
		String userType = user.getType();
		param.put("userType", userType);
		param.put("regionIds", regionService.getChildRegionsByUserId(user.getId()));
		return param;
	}
	
	@Override
	public List<User> getUsersByProfessional(String professional) {
		return userDao.getUsersByProfessional(professional);
	}
	@Override
	public String getProfessionals(String key) {
		List<String> pros = userDao.getProfessionals();
		StringBuffer sb = new StringBuffer();
		if (pros != null && !pros.isEmpty()) {
			for (String pro : pros) {
				if (pro.equals(key))
					continue;
				if (sb.length() > 0)
					sb.append(",").append(pro);
				else
					sb.append(pro);
			}
			return sb.toString();
		}
		return null;
	}

	@Override
	public List<User> findProList(PaginationAble page, User user) {
		loadAuth(page.getWhereParameters(),user);
		return userDao.findProList(page, new RowBounds(page.getCurrentResult(), page.getPageSize()));
	}

	@Override
	public List<User> findAccountantList(PaginationAble page, User user) {
		loadAuth(page.getWhereParameters(),user);
		return userDao.findAccountantList(page, new RowBounds(page.getCurrentResult(), page.getPageSize()));
	}
	
	@Override
	public Integer validateUser(String userName) {
		return userDao.validateUser(userName);
	}

	@Override
	public List<User> findUserByIds2(String[] ids) {
		return userDao.findUserByIds2(ids);
	}
	
	@Override
	public String findAllMembers() {
		List<String> ids = userDao.findAllMembers();
		if (ids != null && !ids.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				if (i == 0)
					sb.append(id);
				else
					sb.append(",").append(id);
			}
			return sb.toString();
		} else
			return null;
	}

	@Override
	public User findUserByOpenId(String openId) {
		return userDao.findUserByOpenId(openId);
	}

	@Override
	public Integer selectCount(String hospitalId) {
		return userDao.selectCount(hospitalId);
	}

	@Override
	public List<User> findUsersByPage(PaginationAble paginationAble, User user) {
		loadAuth(paginationAble.getWhereParameters(),user);
		return userDao.findUsersByPage(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}
	
	
	public User cleanUser(User user){
		if(user != null){
			if(user.getExtUser() != null){
				if(user.getExtUser().getOrganization() != null){
					Hospital hospital = user.getExtUser().getOrganization();
					hospital.setAgencyOverviews(null);//置空医院介绍
					hospital.setIntroduction(null);//置空简介
				}
			}
		}
		return user;
	}
	
}
