package com.platform.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author wangying
 */
@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="id",type=IdType.AUTO)
	private Integer id;

	private String address;

	private String phone;

	private String status;

	private String owner;

	private LocalDateTime createdDate;

	private BigDecimal totalMoney;

}
