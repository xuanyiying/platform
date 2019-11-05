package com.platform.message.service;

import com.platform.common.exception.AuthFailedException;
import com.platform.orm.entity.User;

/**
 * @author wangying
 */
public interface AuthService {
	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws AuthFailedException
	 */
	User auth(String userName, String password) throws AuthFailedException;

}
