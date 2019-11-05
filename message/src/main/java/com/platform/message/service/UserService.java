package com.platform.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.message.vo.UserVO;
import com.platform.orm.entity.User;
import java.util.List;

/**
 * @author wangying Created on 2019/9/25.
 */
public interface UserService extends IService<User> {
	/**
	 * Find user by username
	 * 
	 * @param username
	 * @return
	 */
	User getByUsername(String username);

	UserVO findById(Integer id);

	/**
	 * 注册
	 * 
	 * @param user
	 * @throws UniqueException
	 */
	void register(UserVO user) throws UniqueException;

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 */
	void resetPassword(Integer id, String password);

	/**
	 * 修改用户权限
	 * 
	 * @param user
	 */
	void updatePriority(UserVO user);

	/**
	 *
	 * @param priority
	 * @return
	 */
	List<Integer> getUserPriorities(Integer priority);
}
