package com.platform.orm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangying
 * @since 2019-10-25
 */
@Data
@TableName("sys_user")
public class SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String username;

	private String phone;

	private String password;

	private String avatarUrl;

	private Long roleId;

	private Long groupId;

}
