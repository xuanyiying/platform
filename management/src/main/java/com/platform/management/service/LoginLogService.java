package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.LoginLog;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
public interface LoginLogService extends IService<LoginLog> {
    /**
     *
     * @param loginLog
     * @param update
     */
    void checkUnique(LoginLog loginLog, boolean update) throws UniqueException;

    /**
     *
     * @param pageParam
     * @return
     */
    PageInfo<LoginLog> findByPage(PageParameter<LoginLog> pageParam);
}
