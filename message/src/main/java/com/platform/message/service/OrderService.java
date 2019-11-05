package com.platform.message.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.message.vo.OrderVO;
import com.platform.orm.entity.Order;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
public interface OrderService extends IService<Order> {
	/**
	 * 获取用户的所有订单
	 *
	 * @param username
	 * @return
	 */
	List<Order> getOrderByUsername(String username);

	/**
	 * 保存订单
	 *
	 * @param order
	 */
	void saveOrder(OrderVO order);

	/**
	 *
	 * @param id
	 */
	void commitOrder(Integer id);

	/**
	 *
	 * @param id
	 */
    void approvalOrder(Integer id);

	/**
	 *
	 * @param id
	 */
	void cancelOrder(Integer id);

	/**
	 *
	 * @param id
	 * @return
	 */
	OrderVO findById(Integer id);
}
