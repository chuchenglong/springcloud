package com.mc.controller;

import com.mc.mapper.UserInfoMapper;
import com.mc.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/04/25 15:51
 */
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @GetMapping("/{id}")
    public UserInfo getUser(@PathVariable int id) throws Exception {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }

}
