package com.mc.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/04/27 18:24
 */
@Configuration
public class FeignConfig {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
