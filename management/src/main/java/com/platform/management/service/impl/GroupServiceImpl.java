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
import com.platform.management.service.GroupService;
import com.platform.orm.entity.Group;
import com.platform.orm.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@Service
public class GroupServiceImpl extends ServiceImpl <GroupMapper, Group> implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    /**
     * @param group
     * @param update
     */
    @Override
    public void checkUnique(Group group, boolean update) throws UniqueException {
        CheckUnique.checkUnique(group,groupMapper,update,"groupName");
    }

    /**
     * @param of
     * @return
     */
    @Override
    public PageInfo<Group> findByPage(PageParameter<Group> of) {
        QueryWrapper<Group> queryWrapper = QueryBuilder.build(of, null, true);
        Page<Group> page = new Page <>(of.getPage(), of.getLimit());
        IPage<Group> result = groupMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }
}
