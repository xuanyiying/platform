package com.platform.orm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author wangying
 * @since 2019-10-29
 */
@Data
public class LoginLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 登录时间
	 */
	private LocalDateTime loginTime;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 登录状态, 0 表示登录失败, 1 表示登录成功.
	 */
	private String loginStatus;

	/**
	 * IP
	 */
	private String ip;

}
