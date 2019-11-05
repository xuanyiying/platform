package com.platform.common.interceptor;

import com.platform.common.RequestConstant;
import com.platform.common.annotation.Authentication;
import com.platform.common.token.TokenManager;
import com.platform.common.util.StringUtil;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author wangying
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager tokenManager;

    private RedisTemplate <Integer, String> redisTemplate;

    public AuthenticationInterceptor(@Qualifier("redisTemplate") RedisTemplate <Integer, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        boolean result = false;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(RequestConstant.HEADER_TOKE);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authentication authentication = method.getAnnotation(Authentication.class);

        boolean login = StringUtil.isNotEmpty(token);
        // 1 Don't need login method
        if (Objects.isNull(authentication)) {
            return true;
        } else if (!login) {
            // 2. Need login method
            response.setStatus(403);
            return result;
        }
        // 3 Check user token
        Integer userId;
        try {
            userId = tokenManager.verifyAndParseToken(token);
        } catch (Exception e) {
            response.setStatus(401);
            return result;
        }
        String redisToken = redisTemplate.boundValueOps(userId).get();
        result = token.equals(redisToken);
        if (!(result)) {
            response.setStatus(401);
        }
        return result;
    }

}
