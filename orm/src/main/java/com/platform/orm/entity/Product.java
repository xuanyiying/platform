package com.platform.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import java.math.BigDecimal;
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
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="id",type=IdType.AUTO)
	private Integer id;

	private String name;
	/**
	 * 商品描述
	 */
	private String description;
	/**
	 * 销量
	 */
	private String saleNum;
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 折扣
	 */
	private BigDecimal discount;
	/**
	 * 图片路径
	 */
	private String imageUrl;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 *  状态
	 */
	private String status;

}
