package com.platform.orm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 *
 * @author wangying
 * @since 2019-10-29
 */
@Data
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 *
	 */
	private String groupName;

	/**
	 * 上级部门ID
	 */
	private Integer parentId;

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

}
