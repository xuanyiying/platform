package com.platform.orm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>
 * 角色-操作关系表
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
@Data
public class RoleOperation implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private Integer roleId;

	private Integer operationId;

}
