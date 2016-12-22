package com.zyt.web.after.position.service.impl;

import httl.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.position.service.PositionService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.position.bean.Position;
import com.zyt.web.publics.module.position.dao.PositionDao;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * 
 * @author LiuChuang
 * @description 岗位服务层
 * @version 1.0
 * @date 2015年4月20日
 */
@Service
public class PositionServiceImpl implements PositionService{
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private IRegionService regionService;

	@Override
	public int save(Position position) {
		position.setApplyTime(new Date());
		position.setPositionId(UUIDUtils.getUUID());
		return positionDao.save(position);
	}

	@Override
	public int delete(String[] ids) {
		if(ids == null || ids.length <= 0)
			return 0;
		return positionDao.delete(ids);
	}

	@Override
	public int update(Position position) {
		return positionDao.update(position);
	}

	@Override
	public List<Position> findList(PaginationAble paginationAble, User user) {
		userService.loadAuth(paginationAble.getWhereParameters(),user);
		return positionDao.findList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public Position getById(String id) {
		return positionDao.getById(id);
	}

	@Override
	public List<Position> getByOrgIds(String[] orgIds) {
		return positionDao.getByOrgIds(orgIds);
	}

	@Override
	public List<Position> getByUser(User user) {
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
		
		return positionDao.getByUser(params);
	}
	
}
