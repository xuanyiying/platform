package com.platform.message.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.platform.orm.entity.OrderItem;

import lombok.Data;

/**
 * @author wangying Created on 2019/10/23.
 */
@Data
public class OrderVO {

	private Integer id;

	private String address;

	private String phone;

	private String status;

	private String owner;

	private LocalDateTime createdDate;

	private BigDecimal totalMoney;

	private List<OrderItem> items;
}
