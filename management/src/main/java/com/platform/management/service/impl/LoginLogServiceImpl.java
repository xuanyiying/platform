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
import com.platform.management.service.LoginLogService;
import com.platform.orm.entity.LoginLog;
import com.platform.orm.mapper.LoginLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public PageInfo <LoginLog> findByPage(PageParameter <LoginLog> of) {
        QueryWrapper<LoginLog> queryWrapper = QueryBuilder.build(of, null, true);
        Page<LoginLog> page = new Page <>(of.getPage(), of.getLimit());
        IPage<LoginLog> result = loginLogMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }

    /**
     * @param loginLog
     * @param update
     */
    @Override
    public void checkUnique(LoginLog loginLog, boolean update) throws UniqueException {
        CheckUnique.checkUnique(loginLog, loginLogMapper, update, "username", "phone");
    }
}
