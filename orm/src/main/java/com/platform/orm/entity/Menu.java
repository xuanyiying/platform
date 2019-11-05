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
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 *
	 */
	private Integer parentId;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单 URL
	 */
	private String url;

	/**
	 * 权限标识符
	 */
	private String perms;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;

	/**
	 * 图标
	 */
	private String icon;

}
