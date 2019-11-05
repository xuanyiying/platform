package com.platform.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.CheckUnique;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.common.pagination.QueryBuilder;
import com.platform.management.service.RoleService;
import com.platform.orm.entity.Role;
import com.platform.orm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo <Role> findByPage(PageParameter <Role> of) {
        QueryWrapper<Role> queryWrapper = QueryBuilder.build(of, null, true);
        Page<Role> page = new Page <>(of.getPage(), of.getLimit());
        IPage<Role> result = roleMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }

    /**
     * @param role
     * @param update
     */
    @Override
    public void checkUnique(Role role, boolean update) throws UniqueException {
        CheckUnique.checkUnique(role, roleMapper, update, "roleName");
    }
}
