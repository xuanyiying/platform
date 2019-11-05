package com.platform.message.handler;

import com.platform.orm.entity.User;

/**
 * @author wangying
 * Created on 2019/10/25.
 */
public abstract class  AbstractHandler {
    public abstract boolean hasPermission(User user);

    public abstract boolean hasPermission(String username);
}
