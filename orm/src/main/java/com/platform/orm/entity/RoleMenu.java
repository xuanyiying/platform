package com.platform.orm.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
@Data
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer roleId;

	private Integer menuId;

}
