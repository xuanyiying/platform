package com.platform.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wangying
 * Created on 2019/10/29.
 */

@SpringBootApplication
@MapperScan("com.platform.orm.mapper")
@ComponentScan("com.platform.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

}

