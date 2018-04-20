package com.mc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenglongChu
 * @description Mybatis接入第二个数据库连接
 * @create 2017/11/27 14:19
 * @since v0.1
 */
@Configuration
@MapperScan(basePackages = "com.mc.mapperSec", sqlSessionFactoryRef = "secSqlSessionFactory", sqlSessionTemplateRef = "secSqlSessionTemplate")
public class ConfigMybatisSec {
}
