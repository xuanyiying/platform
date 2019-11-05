package com.platform.message.controller;

import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.message.handler.UserPriorityHandler;
import com.platform.message.service.OrderService;
import com.platform.message.vo.OrderVO;
import com.platform.orm.entity.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangying Created on 2019/10/23.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    @Authentication
    public OrderVO getOrderById(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    @PostMapping("/order")
    @Authentication
    public RequestResult saveOrder(@RequestBody OrderVO order) {
        orderService.saveOrder(order);
        return RequestResult.success();
    }

    @GetMapping("/order")
    @Authentication
    public List <Order> getOrderByUsername(@RequestParam("username") String username) {
        return orderService.getOrderByUsername(username);
    }

    @PutMapping("/order/approval/{id}")
    @Authentication
    public RequestResult approvalOrder(@PathVariable Integer id) {
        orderService.approvalOrder(id);
        return RequestResult.success();
    }

    @PutMapping("/order/commit/{id}")
    @Authentication
    public RequestResult commitOrder(@PathVariable Integer id) {
        orderService.commitOrder(id);
        return RequestResult.success();
    }
    @PutMapping("/order/cancel/{id}")
    @Authentication
    public RequestResult cancelOrder(@PathVariable Integer id) {
        orderService.cancelOrder(id);
        return RequestResult.success();
    }
}
