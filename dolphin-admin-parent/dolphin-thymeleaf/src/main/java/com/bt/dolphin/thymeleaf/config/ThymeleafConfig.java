package com.bt.dolphin.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bt.dolphin.thymeleaf.DolphinDialect;

@Configuration
public class ThymeleafConfig {

    /**
     * 配置自定义的CusDialect，用于整合thymeleaf模板
     */
    @Bean
    public DolphinDialect getDolphinDialect(){
        return new DolphinDialect();
    }
}
