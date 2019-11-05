package com.platform.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
@Data
public class UserRelationship implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="id",type=IdType.AUTO)
	private Integer id;

	private Integer parentId;

	private Integer childId;

	private String invitedNum;

}
