package com.mc;

import com.netflix.zuul.ZuulFilter;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/05/04 16:46
 */
public class PreRequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("123456789");
        return null;
    }
}
