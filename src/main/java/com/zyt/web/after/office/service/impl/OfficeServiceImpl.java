package com.zyt.web.after.office.service.impl;

import httl.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.office.service.OfficeSerrvice;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.office.bean.Office;
import com.zyt.web.publics.module.office.dao.OfficeDao;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * 
 * @author LiuChuang
 * @description 职务服务层
 * @version 1.0
 * @date 2015年4月20日
 */
@Service
public class OfficeServiceImpl implements OfficeSerrvice{
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private IRegionService regionService;
	
	@Override
	public int save(Office office) {
		office.setApplyTime(new Date());
		office.setOfficeId(UUIDUtils.getUUID());
		return officeDao.save(office);
	}

	@Override
	public int delete(String[] ids) {
		if(ids == null || ids.length <= 0)
			return 0;
		return officeDao.delete(ids);
	}

	@Override
	public int update(Office office) {
		return officeDao.update(office);
	}

	@Override
	public List<Office> findList(PaginationAble paginationAble, User user) {
		userService.loadAuth(paginationAble.getWhereParameters(),user);
		return officeDao.findList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public Office getById(String id) {
		return officeDao.getById(id);
	}

	@Override
	public List<Office> getByOrgIds(String[] orgIds) {
		return officeDao.getByOrgIds(orgIds);
	}

	@Override
	public List<Office> getByUser(User user) {
		if(user == null)
			return null;
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("userType", user.getType());
		if("100".equals(user.getType())){//区域用户
			params.put("regionIds", regionService.getChildRegionsByUserId(user.getId()));
		}else if(Integer.valueOf(user.getType())  >= 200 && Integer.valueOf(user.getType()) < 300){//机构用户 ，此处有些特殊，因为职务不管是此单位的任何机构创建的，属于此机构的用户都可以看到所有的职务
			Hospital organization = user.getExtUser().getOrganization();
			List<Hospital> hospitals = null;
			if(organization != null){
				if (StringUtils.isBlank(organization.getParentsId()) || "0".equals(organization.getParentsId())){ //顶级机构
					hospitals = hospitalService.getOrganizationByParentsId(organization.getHospitalId());
				} else {//非顶级
					String tempIds[] = organization.getParentsId().split(","); 
					String organizationId = tempIds[tempIds.length -1];
					hospitals = hospitalService.getOrganizationByParentsId(organizationId);
				}
			}
			params.put("orgIds",hospitals);
		}
		
		return officeDao.getByUser(params);
	}
	
}
