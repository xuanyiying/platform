package com.platform.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangying
 * Created on 2019/10/29.
 */

@SpringBootApplication
@MapperScan("com.platform.orm.*")
@ComponentScan("com.platform.*")
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}

