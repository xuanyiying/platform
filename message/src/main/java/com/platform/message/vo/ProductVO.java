package com.platform.message.vo;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author wangying
 * Created on 2019/11/4.
 */
@Data
public class ProductVO {

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
    /**
     * 图片
     */
    private String imageData;
}
