package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Group;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
public interface GroupService extends IService <Group> {
    /**
     * @param group
     * @param b
     */
    void checkUnique(Group group, boolean b) throws UniqueException;

    /**
     * @param pageParam
     * @return
     */
    PageInfo <Group> findByPage(PageParameter <Group> pageParam);
}
