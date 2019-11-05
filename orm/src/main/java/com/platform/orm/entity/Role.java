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
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String roleName;

	private String remark;

	private LocalDateTime createTime;

	private LocalDateTime modifyTime;

}
