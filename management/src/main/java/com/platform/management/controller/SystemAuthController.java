package com.platform.management.controller;

import com.platform.common.AuthResult;
import com.platform.common.ResultStatus;
import com.platform.common.annotation.Authentication;
import com.platform.common.token.TokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangying Created on 2019/10/25.
 */
@RestController
public class SystemAuthController {
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/manage/auth")
    public ResponseEntity <AuthResult> login(@RequestParam(value = "username") String username, @RequestParam(value =
            "password") String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new ResponseEntity <>(AuthResult.error(ResultStatus.USER_NOT_EXIST), HttpStatus.BAD_REQUEST);

        } catch (IncorrectCredentialsException e) {
            return new ResponseEntity <>(AuthResult.error(ResultStatus.USERNAME_OR_PASSWORD_INVALID), HttpStatus
                    .BAD_REQUEST);
        }
        AuthResult result = AuthResult.ok(token, token.getUsername());
        return new ResponseEntity <>(result, HttpStatus.OK);

    }

    @DeleteMapping("/manage/logout/{id}")
    @Authentication
    public ResponseEntity <AuthResult> logout(@PathVariable(value = "id") Integer id) {
        tokenManager.removeToken(id);
        return new ResponseEntity <>(AuthResult.ok(), HttpStatus.OK);
    }
}
