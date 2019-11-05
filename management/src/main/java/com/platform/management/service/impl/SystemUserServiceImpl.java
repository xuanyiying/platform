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
import com.platform.management.service.SystemUserService;
import com.platform.orm.entity.SystemUser;
import com.platform.orm.mapper.OperationMapper;
import com.platform.orm.mapper.RoleMapper;
import com.platform.orm.mapper.SystemUserMapper;
import java.util.Set;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl <SystemUserMapper, SystemUser> implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private OperationMapper operationMapper;

    @Override
    public PageInfo <SystemUser> findByPage(PageParameter <SystemUser> of) {
        QueryWrapper <SystemUser> queryWrapper = QueryBuilder.build(of, null, true);
        Page <SystemUser> page = new Page <>(of.getPage(), of.getLimit());
        IPage <SystemUser> result = systemUserMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }

    /**
     * @param systemUser
     * @param update
     */
    @Override
    public void checkUnique(SystemUser systemUser, boolean update) throws UniqueException {
        CheckUnique.checkUnique(systemUser, systemUserMapper, update, "username", "phone");
    }

    /**
     * @param username
     * @return
     */
    @Override
    public Set <String> findPermissionsByUsername(String username) {
        SystemUser user = systemUserMapper.findByUsername(username);
        return operationMapper.findPermissionsByUserId(user.getId());

    }

    /**
     * 根据用户名查询角色名
     *
     * @param username
     * @return
     */
    @Override
    public Set <String> findRoleNameByUserName(String username) {
        SystemUser user = systemUserMapper.findByUsername(username);
        return roleMapper.findRoleNameByUserId(user.getId());
    }
    @Override
    public boolean save(SystemUser user){
        String salt = String.valueOf(System.currentTimeMillis());
        user.setPassword(new Md5Hash(user.getPassword(),salt).toHex());
       return  super.save(user);
    }
}
