package com.mc.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author chenglongchu
 * @description 简化常用SQL的工具
 * @create 2018/04/11 16:14
 * @since v0.3
 */
public interface ConfigQkMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // TODO
    // FIXME 特别注意，该接口不能被扫描到，否则会出错
}
