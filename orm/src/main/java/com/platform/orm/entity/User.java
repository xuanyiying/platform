package com.platform.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * User
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户级别（权限）
     */
    private Integer priority;
    /**
     * 自己的邀请码
     */
    private String myInvitationNum;
    /**
     * 图像地址
     */
    private String avatarUrl;

}
