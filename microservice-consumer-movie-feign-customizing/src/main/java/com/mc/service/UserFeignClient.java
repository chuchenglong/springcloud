package com.mc.service;

import com.mc.model.UserInfo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/04/27 17:53
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignClientsConfiguration.class)
public interface UserFeignClient {
    @RequestLine("GET/{id}")
    public UserInfo findById(@Param("id") int id);
}
