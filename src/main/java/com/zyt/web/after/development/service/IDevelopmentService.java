package com.zyt.web.after.development.service;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.BaseService;
import com.zyt.web.publics.module.development.bean.Development;

/**
 * @Description:企业发展管理业务层接口
 * @ClassName:  IDevelopmentService
 * @author: sunshine  
 */
public interface IDevelopmentService extends BaseService<Development> {

	public List<Development> getYearExport(Map<String,String> map);
}
