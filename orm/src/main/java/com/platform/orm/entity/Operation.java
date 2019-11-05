package com.platform.orm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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
public class Operation implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 所属菜单 ID
	 */
	private Integer menuId;

	/**
	 * 资源名称
	 */
	private String operationName;

	/**
	 * 资源 URL
	 */
	private String url;

	/**
	 * 权限标识符
	 */
	private String permission;

	/**
	 * 资源需要的 HTTP 请求方法
	 */
	private String httpMethod;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;

}
