package com.mc.controller;

import com.mc.model.UserInfo;
import com.mc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ChenglongChu
 * @description spring cloud demo
 * @create 2018/04/20 16:41
 */
@RestController
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @GetMapping("/{id}")
    public void findById(@PathVariable int id) throws Exception {
        UserInfo userInfo = userService.findById(id);
        commOutput(httpServletResponse, userInfo);
    }
}
