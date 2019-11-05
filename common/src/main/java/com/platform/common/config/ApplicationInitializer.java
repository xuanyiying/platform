
package com.platform.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author wangying
 */
@Configuration
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) {
        // Load Spring web application configuration
        // Create and register the DispatcherServlet
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
    @Bean(name="redisTemplate")
    public RedisTemplate<Integer, String> redisTemplate (){
       RedisTemplate<Integer, String> redisTemplate =  new  RedisTemplate<>();
        redisTemplate.setConnectionFactory(new JedisConnectionFactory());
        return redisTemplate;
    }
}
