package com.mc.controller;

import com.mc.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient load;
    @GetMapping("/user/{id}")
    public UserInfo findById(@PathVariable int id) throws Exception {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        UserInfo userInfo = restTemplate.getForObject("http://microservice-provider-user/" + id, UserInfo.class);
        return userInfo;
    }

    @GetMapping("/log-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.load.choose("microservice-provider-user");
        MovieController.LOGGER.info("{} : {} : {}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    @GetMapping("/log-instance-string")
    public String logUserInstanceString() {
        ServiceInstance serviceInstance = this.load.choose("microservice-provider-user");
        return serviceInstance.getServiceId() + "   " + serviceInstance.getHost() + "   " + serviceInstance.getPort();
    }

}
