package com.platform.common.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author wangying
 * Created on 2019/10/25.
 */
public final class WebUtil {
    private WebUtil() {
    }
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return attributes.getRequest();
    }

    public static ApplicationContext getApplicationContext(){
        ServletContext sc = getRequest().getServletContext();
        return WebApplicationContextUtils.getWebApplicationContext(sc);
    }
}
