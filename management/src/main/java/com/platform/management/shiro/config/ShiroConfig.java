package com.platform.management.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.platform.management.shiro.realm.UserRealm;

/**
 * @author wangying
 */
@Configuration
public class ShiroConfig {

    /**
             *  常用过滤器:
     *   anon->无需登录可以访问; 
     *   authc->必须认证才可访问;
     *   user-> 如果使用rememberMe的功能可以直接访问;
     *   perms->该资源必须得到资源认证才可访问;
     *   role->该资源必须得到角色权限才可访问;
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
	    @Qualifier("securityManager") SecurityManager securityManager) {
	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	shiroFilterFactoryBean.setSecurityManager(securityManager);
	shiroFilterFactoryBean.setLoginUrl("/login");
	shiroFilterFactoryBean.setUnauthorizedUrl("/403");
	Map<String, String> filters = new LinkedHashMap<>();
	// 拦截
	filters.put("/*", "authc");
	// 放行
	filters.put("/login", "anon");
	shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
	return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	// 关联realm
	securityManager.setRealm(userRealm);
	return securityManager;
    }

    /**
     * 用户名密码登录 Realm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
	return new UserRealm();
    }

}