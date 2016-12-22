package com.zyt.web.after.sysmanager.service.impl;

import httl.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.publics.module.sysmanager.bean.Region;
import com.zyt.web.publics.module.sysmanager.dao.RegionDao;
import com.zyt.web.publics.utils.CacheModuleConstants;
import com.zyt.web.publics.utils.UUIDUtils;
/**
 * 区域业务层实现类
 * @ClassName:  RegionServiceImpl   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月12日 上午11:04:13
 */
@Service
public class RegionServiceImpl implements IRegionService {
	
	@Autowired
    private RegionDao regionDao;
	
	@Override
	@Cacheable(value=CacheModuleConstants.REGION)
	public List<Region> findList( boolean isgetRoot) {
		List<Region> list=new ArrayList<Region>(); 
		if(isgetRoot){
			Region rg=new Region();
			rg.setId("0");
			rg.setParentId("-1");
			rg.setRegionName("区域管理");
			rg.setFormatCode("`");
			rg.setLevelIndex(0);
			rg.setLevelSeq(0);
			list.add(rg);
			list.addAll(regionDao.getRegions());
		}else{
			list=regionDao.getRegions();
		}
		
		return list;
	}

	@Override
	@CacheEvict(value=CacheModuleConstants.REGION,allEntries=true)
	public int save(Region region) {
		int rt=0;
		if(StringUtils.isEmpty(region.getId())){
			String id=UUIDUtils.getUUID();
			region.setId(id);
			region.setFormatCode(region.getFormatCode()+id+"`");
			rt=regionDao.insertRegion(region);
		}else{
			rt=regionDao.updateRegion(region);
		}
		return rt;
	}

	@Override
	@CacheEvict(value=CacheModuleConstants.REGION,allEntries=true)
	public int delete(Region region) {
		int delcount=0;
		if(!StringUtils.isEmpty(region.getId())){
			delcount=regionDao.deleteRegion(region.getId());
		}
		return delcount;
	}

	@Override
	public Region getRegionById(Serializable id) {
		return regionDao.getRegionById(id);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.REGION)
	public List<Region> getRegionsByUserId(String userId) {
		if(StringUtils.isBlank(userId))
			return null;
		return regionDao.getRegionByUserId(userId);
	}


	@Override
	public String[] getChildRegionsByUserId(String userId) {
		String [] regions = null;
		List<Region> regionsList = this.getRegionsByUserId(userId);
		if (regionsList != null && !regionsList.isEmpty()) {
			regions = new String[regionsList.size()];
			for (int i = 0; i < regionsList.size(); i++) {
				Region reg = regionsList.get(i);
				regions[i] = reg.getId();
			}
		}
		return regions;
	}

	@Override
	public List<Region> getRegionByParents(String id) {
		return regionDao.getRegionByParents(id);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.REGION)
	public List<Region> getRegionByParentId(String parentId,String regionName) {
		return regionDao.getRegionByParentId(parentId,regionName);
	}

	@Override
	public Region getRegionByName(String regionName) {
		return regionDao.getRegionByName(regionName);
	}
	
}
