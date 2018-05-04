package com.mc.controller;

import com.mc.model.UserInfo;
import com.mc.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/04/25 17:07
 */
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public UserInfo findById(@PathVariable int id) throws Exception {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        UserInfo userInfo = restTemplate.getForObject("http://chuchenglong:8001/" + id, UserInfo.class);
        return userInfo;
    }

    @GetMapping("/user/feign/{id}")
    public UserInfo feignFindById(@PathVariable int id) throws Exception {
        return userFeignClient.feignFindById(id);
    }
}
