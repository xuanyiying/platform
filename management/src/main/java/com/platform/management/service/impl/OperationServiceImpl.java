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
import com.platform.management.service.OperationService;
import com.platform.orm.entity.Operation;
import com.platform.orm.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper,Operation> implements OperationService {
   
    @Autowired
    private OperationMapper operationMapper;

    @Override
    public PageInfo <Operation> findByPage(PageParameter <Operation> of) {
        QueryWrapper<Operation> queryWrapper = QueryBuilder.build(of, null, true);
        Page<Operation> page = new Page <>(of.getPage(), of.getLimit());
        IPage<Operation> result = operationMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }

    /**
     * @param operation
     * @param update
     */
    @Override
    public void checkUnique(Operation operation, boolean update) throws UniqueException {
        CheckUnique.checkUnique(operation, operationMapper, update, "operationName");
    }
}
