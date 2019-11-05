package com.platform.orm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.SystemUser;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangying
 * @since 2019-10-25
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
	/**
	 *
	 * @param username
	 * @return
	 */
	SystemUser findByUsername(String username);

}
