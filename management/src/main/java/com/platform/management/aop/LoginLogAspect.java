package com.platform.management.aop;


import com.platform.common.util.IPUtil;
import com.platform.management.service.LoginLogService;
import com.platform.orm.entity.LoginLog;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wangying
 */
@Aspect
@Component
public class LoginLogAspect {
    @Resource
    private LoginLogService loginLogService;

    @Pointcut("execution(* com.platform.management.controller.SystemAuthController.login(..) )")
    public void loginLogPointCut() {
    }

    @After("loginLogPointCut()")
    public void processJoinPoint(JoinPoint joinPoint) {
        buildAndSaveLog(joinPoint);

    }

    private void buildAndSaveLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        Subject subject = SecurityUtils.getSubject();
        String ip = IPUtil.getIpAddress();
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ip);
        loginLog.setUsername(username);
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setLoginStatus(subject.isAuthenticated() ? "1" : "0");
        loginLogService.save(loginLog);
    }
}