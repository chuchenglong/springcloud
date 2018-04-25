package com.mc.config;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author ChenglongChu
 * @description 统一管理结果结构
 * @create 2017/12/13 14:21
 * @since v0.1
 */
public class ManagerResult<T> implements Serializable{
    private Code code;
    private String message;
    private T data;

    public enum Code {
        success, failed
    }

    public ManagerResult() {
        super();
    }

    public ManagerResult(Code code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @description 组装成功结果结构
     * @param data 参数数据
     * @author ChenglongChu
     * @create 2017/12/14 15:37
    **/
    public static <T> ManagerResult<T> newSuccess(T data) {
        if (null == data) {
            return new ManagerResult(Code.success, "", data);
        }
        if (data.getClass().getTypeName().equals("com.mc.family.config.ManagerResult")) {
            return (ManagerResult<T>)data;
        }
        return new ManagerResult(Code.success, "", data);
    }

    /**
     * @description 组装失败结构结构
     * @param message 信息数据
     * @param data 参数数据
     * @author ChenglongChu
     * @create 2017/12/14 17:41
    **/
    public static <T> ManagerResult<T> newFailed(String message, T data) {
        return new ManagerResult(Code.failed, message, data);
    }

    /**
     * @description 组装失败结构结构
     * @param message 信息数据
     * @author ChenglongChu
     * @create 2017/12/14 17:41
     **/
    public static ManagerResult newFailed(String message) {
        return new ManagerResult(Code.failed, message, null);
    }

    /**
     * @description 查看结果是否为成功结果, 不参与序列化输出
     * @author ChenglongChu
     * @create 2017/12/14 17:43
    **/
//    @JsonIgnore
//    @JSONField(serialize = false)
    public boolean isSuccess() {
        return Code.success.equals(this.code);
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
