package com.mc.service;

import com.alibaba.fastjson.JSONObject;
import com.mc.mapper.UserInfoMapper;
import com.mc.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author ChenglongChu
 * @description spring cloud demo
 * @create 2018/04/20 16:43
 */
@Service
public class MovieService {
    @Autowired
    private RestTemplate restTemplate;

    public UserInfo findById(int id) {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String str = restTemplate.getForObject("http://localhost:8001/" + id, String.class);
        JSONObject json = JSONObject.parseObject(str);
        UserInfo user = JSONObject.toJavaObject(json, UserInfo.class);
        return user;
    }
}
