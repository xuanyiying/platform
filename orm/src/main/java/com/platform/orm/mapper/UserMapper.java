package com.platform.orm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */

public interface UserMapper extends BaseMapper<User> {
	/**
	 * 根据username 查询用户
	 * 
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM user where username=#{username}")
	User findByUsername(@Param("username") String username);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 */
	@Update("UPDATE user SET password=#{password} where id=#{id}")
	void resetPassword(@Param("id") Integer id, @Param("password") String password);

	/**
	 *
	 * @param invitedNum
	 * @return
	 */
	@Select("SELECT * FROM user where my_invitation_num=#{invitedNum}")
	User findByInvitationNum(String invitedNum);
}
