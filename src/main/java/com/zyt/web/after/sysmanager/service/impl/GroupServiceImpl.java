package com.zyt.web.after.sysmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zyt.web.after.sysmanager.service.IGroupService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Group;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.module.sysmanager.dao.GroupDao;
import com.zyt.web.publics.utils.UUIDUtils;

@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private IUserService userService;

    @Override
    public List<Group> findList(PaginationAble paginationAble,User user) {
    	userService.loadAuth(paginationAble.getWhereParameters(), user);
        return groupDao.findList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
    }

    @Override
    public Integer save(Group group) {
        group.setId(UUIDUtils.getUUID());
        group.setGroupCode(group.getId());
        group.setEnabled(true);
        Integer row = groupDao.save(group);
		saveGroupRoleLink(group);
        return row;
    }

    @Override
    public Integer delete(Group group) {
        this.deleteGroupRoleLink(group);
        return groupDao.delete(group);
    }

    public Integer deleteGroupRoleLink(Group group) {
        if (group.getRoles() != null && group.getRoles().size() > 0)
            return groupDao.deleteGroupRoleLink(group);
        return 0;
    }

    @Override
    public Integer update(Group group) {
    	deleteGroupRoleLink(group);
		saveGroupRoleLink(group);
        return groupDao.update(group);
    }

    @Override
    public List<Group> findGroupList(String userId) {
        User user = userService.findUserById(userId);
        Assert.notNull(user, "不能查找到该用户：ID " + userId);
        Map<String, Object> param = new HashMap<String, Object>();
        userService.loadAuth(param, user);
        return groupDao.findGroupList(param);
    }

    @Override
    public Group findGroupById(String id) {
        return groupDao.findGroupById(id);
    }

    @Override
    public Integer saveGroupRoleLink(Group group) {
        if (group.getRoles() != null && group.getRoles().size() > 0)
            return groupDao.saveGroupRoleLink(group);
        else
            return 0;
    }

    @Override
    public Integer transform(Integer state, String[] ids) {
        return groupDao.transform(state, ids);
    }

    @Override
    public Integer deleteBatch(String[] ids) {
        return groupDao.deleteBatch(ids);
    }

    @Override
    public List<Group> findGroupByRegion(String regionId) {
        return groupDao.findGroupByRegion(regionId);
    }

}
