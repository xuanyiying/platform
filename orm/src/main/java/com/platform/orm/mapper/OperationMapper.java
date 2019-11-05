package com.platform.orm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.Operation;
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
public interface OperationMapper extends BaseMapper <Operation> {
    /**
     * 查询权限
     *
     * @param userId
     * @return
     */
    @Select("SELECT permission FROM operation WHERE id =(SELECT operation_id  FROM role_operation WHERE " + "role_id=" +
            "(SELECT role_id FROM sys_user_role WHERE user_id=#{userId}))")
    Set <String> findPermissionsByUserId(@Param("userId") Integer userId);
}
