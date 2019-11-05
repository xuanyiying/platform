package com.platform.orm.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.orm.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
public interface OrderMapper extends BaseMapper<Order> {
	/**
	 * 查询用户的所有订单
	 * 
	 * @param owner
	 * @return
	 */
	@Select("SELECT * FROM order_item WHERE owner=#{owner}")
	List<Order> getOrderByUsername(@Param("owner") String owner);

}
