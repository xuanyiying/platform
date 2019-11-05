package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.SystemUser;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangying
 * @since 2019-10-25
 */
public interface SystemUserService extends IService<SystemUser> {
	/**
	 *
	 * @param pageParam
	 * @return
	 */
	PageInfo<SystemUser> findByPage(PageParameter<SystemUser> pageParam);

	/**
	 *
	 * @param systemUser
	 * @param update
	 */
	void checkUnique(SystemUser systemUser, boolean update) throws UniqueException;

	/**
	 *
	 * @param username
	 * @return
	 */
	Set<String> findPermissionsByUsername(String username);

	/**
	 *
	 * @param username
	 * @return
	 */
	Set<String> findRoleNameByUserName(String username);

}
