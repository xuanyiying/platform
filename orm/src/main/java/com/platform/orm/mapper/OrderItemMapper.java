package com.platform.orm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.OrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangying Created on 2019/10/23.
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    /**
     *
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM order_item where order_id = #{orderId}")
    List<OrderItem> findByOrderId(@Param("orderId") Integer orderId);
}
