package com.mc.controller;

import com.mc.model.UserInfo;
import com.mc.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MovieController extends BaseController{
    @Autowired
    private MovieService movieService;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @GetMapping("/user/{id}")
    public void findById(@PathVariable int id) throws Exception {
        UserInfo userInfo = movieService.findById(id);
        commOutput(httpServletResponse, userInfo);
    }
}
