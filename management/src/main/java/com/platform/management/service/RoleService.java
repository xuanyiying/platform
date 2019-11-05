package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Role;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
public interface RoleService extends IService<Role> {
    /**
     *
     * @param role
     * @param update
     */
    void checkUnique(Role role, boolean update) throws UniqueException;

    /**
     *
     * @param pageParam
     * @return
     */
    PageInfo<Role> findByPage(PageParameter<Role> pageParam);
}
