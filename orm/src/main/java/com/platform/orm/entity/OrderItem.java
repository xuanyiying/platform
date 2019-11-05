package com.platform.orm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * @author wangying Created on 2019/10/23. DROP TABLE IF EXISTS `order_item`;
 *         CREATE TABLE `order_item` ( `id` bigint(20) NOT NULL, `order_id`
 *         bigint(20), `product_id` bigint(20), `num` int, `unit_price` DECIMAL,
 *         `discount` DECIMAL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT
 *         CHARSET=utf8;
 */
@Data
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private Integer orderId;

	private Integer productId;

	private Integer num;

	private BigDecimal unitPrice;

	private BigDecimal discount;

}
