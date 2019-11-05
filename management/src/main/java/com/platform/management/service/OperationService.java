package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Operation;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
public interface OperationService extends IService<Operation> {
    /**
     *
     * @param operation
     * @param b
     */
    void checkUnique(Operation operation, boolean b) throws UniqueException;
    /**
     *
     * @param pageParam
     * @return
     */
    PageInfo<Operation> findByPage(PageParameter<Operation> pageParam);
}
