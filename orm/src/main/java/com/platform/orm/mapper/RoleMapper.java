package com.platform.orm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.Role;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
public interface RoleMapper extends BaseMapper <Role> {
    /**
     * 根据用户id查询角色名
     *
     * @param userId
     * @return
     */
    @Select("SELECT role_name FROM ROLE WHERE id =(SELECT role_id FROM sys_role_user WHERE user_id=#{userId})")
    Set <String> findRoleNameByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id查询
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM ROLE WHERE id =(SELECT role_id FROM sys_role_user WHERE user_id=#{userId})")
    List <Role> findByUserId(@Param("userId")Integer userId);
}
