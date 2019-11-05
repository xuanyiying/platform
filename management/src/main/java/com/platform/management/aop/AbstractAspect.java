package com.platform.management.aop;

import com.alibaba.fastjson.JSONObject;
import com.platform.common.RequestConstant;
import com.platform.common.token.TokenManager;
import com.platform.common.util.IPUtil;
import com.platform.common.util.WebUtil;
import com.platform.orm.entity.SystemLog;
import com.platform.orm.entity.SystemUser;
import com.platform.orm.mapper.SystemLogMapper;
import com.platform.orm.mapper.SystemUserMapper;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author wangying
 * Created on 2019/10/18.
 */
@Slf4j
public abstract class AbstractAspect {
    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    @Autowired
    private SystemLogMapper logMapper;
    @Autowired
    private TokenManager manager;
    @Autowired
    private SystemUserMapper userMapper;

    void doBefore(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] args = pjp.getArgs();
        Method method = signature.getMethod();
        String methodName = method.getName();
        if (methodName.startsWith(UPDATE) && args.length == method.getParameterCount()) {
            buildAndSaveLog(methodName, LogDescription.UPDATE.getDescription());
        } else if (methodName.startsWith(DELETE) && args.length == method.getParameterCount()) {
            buildAndSaveLog(methodName, LogDescription.DELETE.getDescription());
        } else if (methodName.startsWith(ADD) && args.length == method.getParameterCount()) {
            buildAndSaveLog(methodName, LogDescription.ADD.getDescription());
        }
    }

    private SystemUser processUser() {
        String token = WebUtil.getRequest().getHeader(RequestConstant.HEADER_TOKE);
        Integer userId = manager.verifyAndParseToken(token);
        return userMapper.selectById(userId);
    }

    private void buildAndSaveLog(String methodName, String desc) {
        String ip = IPUtil.getIpAddress();
        SystemUser user = processUser();
        SystemLog systemLog = new SystemLog();
        systemLog.setCreateTime(LocalDateTime.now());
        systemLog.setIp(ip);
        systemLog.setUsername(user.getUsername());
        String[] descriptions = desc.split("#");
        systemLog.setOperation(descriptions[0]);
        String tableName = methodName.substring(descriptions[0].length());
        String  description = String.format(descriptions[1], user.getUsername(), tableName);
        String logJson = JSONObject.toJSONString(systemLog);
        systemLog.setParams(description);
        log.info("Add a new system log: {}", logJson);
        logMapper.insert(systemLog);
    }

}
