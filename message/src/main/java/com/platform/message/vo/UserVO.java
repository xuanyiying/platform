package com.platform.message.vo;

import lombok.Data;

/**
 * @author wangying
 * Created on 2019/10/28.
 */
@Data
public class UserVO {
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

    private String invitedNum;

    private String parentUsername;

    private String avatarData;

}
