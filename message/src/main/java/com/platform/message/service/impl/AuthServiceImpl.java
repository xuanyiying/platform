package com.platform.message.service.impl;

import com.platform.common.exception.AuthFailedException;
import com.platform.common.util.SecurityUtil;
import com.platform.message.service.AuthService;
import com.platform.orm.entity.User;
import com.platform.orm.mapper.UserMapper;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User auth(String userName, String password) throws AuthFailedException {
        log.debug("Start check user login information...");
        User user = userMapper.findByUsername(userName);
        if (Objects.nonNull(user)) {
            String dataBasePassword = user.getPassword();
            if (password.equals(SecurityUtil.decrypt(dataBasePassword))) {
                log.debug("User login information verification passed...");
            } else {
                throw new AuthFailedException("Invalid password, please try again!");
            }
        } else {
            throw new AuthFailedException("Invalid username, please try again!");
        }
        return user;
    }

}
