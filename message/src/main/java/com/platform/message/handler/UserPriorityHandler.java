package com.platform.message.handler;

import com.platform.common.RequestConstant;
import com.platform.common.token.TokenManager;
import com.platform.common.util.WebUtil;
import com.platform.message.service.UserService;
import com.platform.orm.entity.User;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangying Created on 2019/10/25.
 */
@Component
public class UserPriorityHandler extends AbstractHandler {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;

    public Integer getUserPriority() {
        HttpServletRequest request = WebUtil.getRequest();
        String token = request.getHeader(RequestConstant.HEADER_TOKE);
        Integer userId = tokenManager.verifyAndParseToken(token);
        User user = userService.getById(userId);
        if (Objects.nonNull(user)) {
            return user.getPriority();
        }
        return -1;
    }

    @Override
    public boolean hasPermission(User user) {
        if (Objects.nonNull(user)) {
            return user.getPriority() < getUserPriority();
        }
        return false;
    }

    @Override
    public boolean hasPermission(String username) {
        User user = userService.getByUsername(username);
        return this.hasPermission(user);
    }
}
