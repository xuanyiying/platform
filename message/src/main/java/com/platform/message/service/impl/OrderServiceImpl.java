package com.platform.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.enumation.OrderStatus;
import com.platform.common.util.BeanUtil;
import com.platform.common.util.Collections;
import com.platform.message.handler.UserPriorityHandler;
import com.platform.message.service.OrderService;
import com.platform.message.vo.OrderVO;
import com.platform.orm.entity.Order;
import com.platform.orm.entity.OrderItem;
import com.platform.orm.mapper.OrderItemMapper;
import com.platform.orm.mapper.OrderMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl <OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserPriorityHandler priorityHandler;

    /**
     * 获取用户的所有订单
     *
     * @param username
     * @return
     */
    @Override
    public List <Order> getOrderByUsername(String username) {
        return orderMapper.getOrderByUsername(username);
    }

    @Override
    public void saveOrder(OrderVO orderVO) {
        Order order = new Order();
        BeanUtil.copy(orderVO, order, "items");
        order.setStatus(OrderStatus.PROIR.name());
        order.setCreatedDate(LocalDateTime.now());
        orderMapper.insert(order);
        List <OrderItem> items = orderVO.getItems();
        if (Collections.nonNull(items)) {
            items.forEach(orderItem -> orderItemMapper.insert(orderItem));
        }
    }

    @Override
    public void commitOrder(Integer id) {
        updateOrder(id, OrderStatus.COMMIT.name());

    }

    /**
     * @param id
     */
    @Override
    public void approvalOrder(Integer id) {
        updateOrder(id, OrderStatus.APPROAL.name());
    }

    /**
     * @param id
     */
    @Override
    public void cancelOrder(Integer id) {
        updateOrder(id, OrderStatus.CANCEL.name());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public OrderVO findById(Integer id) {
        OrderVO vo = new OrderVO();
        Order order = orderMapper.selectById(id);
        BeanUtil.copy(order,vo);
        List <OrderItem> items = orderItemMapper.findByOrderId(id);
        vo.setItems(items);
        return vo;
    }

    private void updateOrder(Integer id, String status) {
        Order order = orderMapper.selectById(id);
        if (priorityHandler.hasPermission(order.getOwner())) {
            order.setStatus(status);
            orderMapper.updateById(order);
        }
    }

}
