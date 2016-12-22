package com.zyt.web.after.hospital.service.impl;
 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.hospital.dao.HospitalDao;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.CacheModuleConstants;
import com.zyt.web.publics.utils.UUIDUtils;

import httl.util.StringUtils;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalDao hospitalDao;
	@Autowired
	IUserService userService;
	
	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION)
	public List<Hospital> findList(Map<String, Object> params) {
		if ("1".equals(params.get("type").toString())) {
			return hospitalDao.getByUserId(params.get("userId").toString());
		} else if("0".equals(params.get("type").toString())){//超管
			return hospitalDao.getAll();
		}else {
			return hospitalDao.getHospitals(params);
		}
	}

	@Override
	@CacheEvict(value=CacheModuleConstants.ORGANIZATION,allEntries=true)
	public Integer save(Hospital hospital) {
		if(StringUtils.isEmpty(hospital.getHospitalId())){
			hospital.setHospitalId(UUIDUtils.getUUID());
			return hospitalDao.insertHospital(hospital);
		}else{
			return hospitalDao.updateHospital(hospital);
		}
	}

	@Override
	public Hospital getById(String id) {
		return hospitalDao.getById(id);
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.ORGANIZATION,allEntries=true)
	public Integer updateHospital(Hospital hospital) {
		//查看机构原属性
		Hospital organ =  hospitalDao.getById(hospital.getHospitalId());
		if(!StringUtils.isBlank(hospital.getParentId()) && !"0".equals(hospital.getParentId())){//现在机构存在父级
			Hospital organization2 =  hospitalDao.getById(hospital.getParentId());
			if(organization2!=null && organization2.getHasChild()==false){
				organization2.setHasChild(true);
				hospitalDao.updateHospital(organization2);//修改现在机构父级属性
			}
		}
		if(!StringUtils.isBlank(organ.getParentId()) && !"0".equals(organ.getParentId())){//原机构存在父级
			List<Hospital> list = hospitalDao.getByParentId(organ.getParentId());
			if(list!=null && list.size()>1){
			}else{
				Hospital organization2 =  hospitalDao.getById(organ.getParentId());
				if(organization2!=null && organization2.getHasChild()==true){
					 organization2.setHasChild(false);
					 hospitalDao.updateHospital(organization2);//修改原机构父级属性
				}
			}
		}
		Integer i = hospitalDao.updateHospital(hospital);
		return i;
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.ORGANIZATION,allEntries=true)
	public Boolean deleteById(String id) {
		return hospitalDao.deleteById(id)> 0 ? true : false;
	}
	@Override
	@CacheEvict(value=CacheModuleConstants.ORGANIZATION,allEntries=true)
	public Boolean deleteByIds(String[] ids) {
		for (String string : ids) {
			if(!StringUtils.isBlank(string)){
				Hospital organization =  hospitalDao.getById(string);
				if(organization!=null && !StringUtils.isBlank(organization.getParentId()) && !"0".equals(organization.getParentId())){
					List<Hospital> list = hospitalDao.getByParentId(organization.getParentId());
					if(list!=null && list.size()>0){
					}else{
						Hospital organization2 =  hospitalDao.getById(organization.getParentId());
						if(organization2!=null && organization2.getHasChild()==true){
							organization2.setHasChild(false);
							hospitalDao.updateHospital(organization2);
						}
					}
				}
			}
		}
		return hospitalDao.deleteByIds(ids)>0?true:false;
	}
	
	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getByParentId'+#parentId")
	public List<Hospital> getByParentId(String parentId) {
		return hospitalDao.getByParentId(parentId);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getChildOrgsByUserId'+#userId")
	public String[] getChildOrgsByUserId(String userId) {
		String[] orgIds = null;
		List<Hospital> orgs = hospitalDao.getByUserId(userId);
		if (orgs != null && !orgs.isEmpty()) {
			orgIds = new String[orgs.size()];
			for (int i = 0; i < orgs.size(); i++) {
				orgIds[i] = orgs.get(i).getHospitalId();
			}
		}
		return orgIds;
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getParentsOrgsByUserId'+#userId")
	public String[] getParentsOrgsByUserId(String userId) {
		String[] orgIds = null;
		User user = userService.findUserById(userId);
		if (user != null) {
			// 获取用户所属机构
			Hospital org = hospitalDao.getById(user
					.getExtUser().getOrgId());
			if (org != null) {
				String tempOrgs[] = null;
				String parentId = org.getParentId();
				//判断是否为根节点
				if (StringUtils.isBlank(parentId) || "0".equals(parentId)) {
					tempOrgs = org.getHospitalId().split(",");
					orgIds = new String[tempOrgs.length];
				} else {
					tempOrgs = org.getParentsId().split(",");
					orgIds = new String[tempOrgs.length + 1];
					orgIds[tempOrgs.length] = org.getHospitalId();
				}
				for (int i = 0; i < tempOrgs.length; i++) {
					orgIds[i] = tempOrgs[i];
				}
			}
		}
		return orgIds;
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'findOrganizationByRegoinId'+#regionId")
	public List<Hospital> findOrganizationByRegoinId(String regionId) {
		return hospitalDao.getByRegoinId(regionId);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION)
	public List<Hospital> getAllOrganization() {
		return hospitalDao.getAll();
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getOrganizationByParentsId'+#parentId")
	public List<Hospital> getOrganizationByParentsId(String parentId) {
		return hospitalDao.getByParentsId(parentId);
	}

	@Override
	public List<Hospital> getOrganizationPageByParentsId(
			PaginationAble paginationAble) {
		return hospitalDao.getHospitalPageByParentsId(paginationAble,  new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getChildOrganizationById'+#id")
	public List<Hospital> getChildOrganizationById(String id) {
		return hospitalDao.getChildById(id);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION)
	public List<Hospital> getOrganizationByRegionIds(String[] regionIds) {
		return hospitalDao.getHospitalByRegionIds(regionIds);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.ORGANIZATION,key="'getParentsOrgsByOrgId'+#orgId")
	public String[] getParentsOrgsByOrgId(String orgId) {
		String[] orgIds = null;
		if (StringUtils.isNotBlank(orgId)) {
			Hospital org = hospitalDao.getById(orgId);
			if (org != null) {
				//临时数组
				String tempOrgs[] = null;
				String parentId = org.getParentId();
				//判断是否为根节点
				if (StringUtils.isBlank(parentId) || "0".equals(parentId)) {
					tempOrgs = orgId.split(",");
					orgIds = new String[tempOrgs.length];
				} else {
					tempOrgs = org.getParentsId().split(",");
					orgIds = new String[tempOrgs.length + 1];
					orgIds[tempOrgs.length] = orgId;
				}
				for (int i = 0; i < tempOrgs.length; i++) {
					orgIds[i] = tempOrgs[i];
				}
			}
		}
		return orgIds;
	}

	@Override
	public List<Hospital> getOrganizationByIds(String[] ids) {
		return hospitalDao.getByIds(ids);
	}

	@Override
	public List<Hospital> getFirstLayer() {
		return hospitalDao.getFirstLayer();
	}

	@Override
	public List<Hospital> findAll(PaginationAble paginationAble) {
		return hospitalDao.findAll(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public List<Hospital> findListByPage(PaginationAble paginationAble, User user) {
		return hospitalDao.findListByPage(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public boolean validCode(String hospitalCode,String hospitalId) {
		Hospital hospital = hospitalDao.findHospitalByCode(hospitalCode,hospitalId);
		return hospital != null?true:false;
	}
	
}
