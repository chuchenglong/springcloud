package com.mc.service;

import com.mc.mapper.UserInfoMapper;
import com.mc.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenglongChu
 * @description spring cloud demo
 * @create 2018/04/20 16:43
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo findById(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
