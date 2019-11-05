package com.platform.common.enumation;

/**
 * @author wangying
 * Created on 2019/10/23.
 */
public enum OrderStatus {
    /**
     * 预下单（用户下单，代理未审批）
     */
    PROIR,
    /**
     * 代理审批,代理完成付款
     */
    APPROAL,
    /**
     * 管理员确认，准备发货
     */
    COMMIT,
    /**
     * 发货
     */
    SHIP,
    /**
     *  取消订单
     */
    CANCEL,
    /**
     *  退款
     */
    REFUND

}
