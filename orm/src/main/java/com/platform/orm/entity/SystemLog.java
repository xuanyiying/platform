package com.platform.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author wangying
 * @since 2019-11-01
 */
@Data
public class SystemLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 操作
     */
    private String operation;

    /**
     * 响应时间/耗时
     */
    private Integer time;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
