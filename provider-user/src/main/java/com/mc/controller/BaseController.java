package com.mc.controller;

import com.alibaba.fastjson.JSONObject;
import com.mc.config.ConstantComm;
import com.mc.config.ManagerLog;
import com.mc.config.ManagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenglongChu
 * @description 提供controller层基础公共方法, 仅提供方法, 不暴露服务
 * @create 2017/12/13 14:11
 * @since v0.1
 */
public class BaseController {
    /**
     * @description 通过request参数为实体对象set值
     * @param request servlet请求参数
     * @param clazz 实体对象的class类型
     * @throws Exception
     * @author ChenglongChu
     * @create 2018/1/30 18:11
    **/
    protected <T> T  input(HttpServletRequest request, Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        input(request, clazz, t);
        while (true) {
            try {
                Class sc = clazz.getSuperclass();
                input(request, sc, t);
                clazz = sc;
            } catch (Exception e) {
                break;
            }
        }
        return t;
    }

    /**
     * @description 通过request参数为实体对象set值
     * @param request servlet请求参数
     * @param t 实体对象
     * @throws Exception
     * @author ChenglongChu
     * @create 2018/1/30 18:11
     **/
    protected <T> T  input(HttpServletRequest request, T t) throws Exception {
        Class clazz = t.getClass();
        // 获取class属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                String attrName = field.getName();

                Object value = request.getParameter(attrName);
                if (value == null) {
                    continue;
                }

                String getMethodName = ConstantComm.GET + attrName.substring(0, 1).toUpperCase()+ attrName.substring(1);
                String setMethodName = ConstantComm.SET + attrName.substring(0, 1).toUpperCase()+ attrName.substring(1);

                Method getMethod = clazz.getMethod(getMethodName);
                Method setMethod = null;

                Type type = getMethod.getGenericReturnType();
                Class<? extends Object> setClass = null;
                if (type instanceof ParameterizedType) {
                    String typeName = type.getTypeName();
                    setClass = Class.forName(typeName.substring(0, typeName.indexOf("<")));
                } else {
                    if (ConstantComm.INT.equals(type.getTypeName())) {
                        setClass = int.class;
                    } else if (ConstantComm.DOUBLE.equals(type.getTypeName())) {
                        setClass = double.class;
                    } else if (ConstantComm.FLOAT.equals(type.getTypeName())) {
                        setClass = float.class;
                    } else if (ConstantComm.BOOLEAN.equals(type.getTypeName())) {
                        setClass = boolean.class;
                    } else if (ConstantComm.CHAR.equals(type.getTypeName())) {
                        setClass = char.class;
                    } else if (ConstantComm.SHORT.equals(type.getTypeName())) {
                        setClass = short.class;
                    } else if (ConstantComm.BYTE.equals(type.getTypeName())) {
                        setClass = byte.class;
                    } else if (ConstantComm.LONG.equals(type.getTypeName())) {
                        setClass = long.class;
                    } else {
                        setClass = Class.forName(type.getTypeName());
                    }
                }

                setMethod = clazz.getMethod(setMethodName, setClass);

                // 处理引用类型转换问题
                if (!value.getClass().getTypeName().equals(type.getTypeName()) && value.getClass().getTypeName().equals(ConstantComm.OBJECT_STRING)) {
                    String tempValue = (String)value;
                    if (type.getTypeName().equals(ConstantComm.OBJECT_INTEGER)) {
                        setMethod.invoke(t, Integer.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_FLOAT)) {
                        setMethod.invoke(t, Float.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_DOUBLE)) {
                        setMethod.invoke(t, Double.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_SHORT)) {
                        setMethod.invoke(t, Short.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_LONG)) {
                        setMethod.invoke(t, Long.valueOf(tempValue));
                    } else {
                        setMethod.invoke(t, value);
                    }
                } else {
                    setMethod.invoke(t, value);
                }

            } catch (Exception e) {
                ManagerLog.error(e, "input属性传输错误 : " , e.getMessage());
                continue;
            }

        }

        return t;
    }

    /**
     * @description 通过request参数为实体对象set值
     * @param request servlet请求参数
     * @param clazz 实体对象的class类型
     * @param t 实体对象
     * @throws Exception
     * @author ChenglongChu
     * @create 2017/12/14 17:47
    **/
    protected <T> void input(HttpServletRequest request, Class clazz, T t) throws Exception {
        // 获取class属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                String attrName = field.getName();

                Object value = request.getParameter(attrName);
                if (value == null) {
                    continue;
                }

                String getMethodName = ConstantComm.GET + attrName.substring(0, 1).toUpperCase()+ attrName.substring(1);
                String setMethodName = ConstantComm.SET + attrName.substring(0, 1).toUpperCase()+ attrName.substring(1);

                Method getMethod = clazz.getMethod(getMethodName);
                Method setMethod = null;

                Type type = getMethod.getGenericReturnType();
                Class<? extends Object> setClass = null;
                if (type instanceof ParameterizedType) {
                    String typeName = type.getTypeName();
                    setClass = Class.forName(typeName.substring(0, typeName.indexOf("<")));
                } else {
                    if (ConstantComm.INT.equals(type.getTypeName())) {
                        setClass = int.class;
                    } else if (ConstantComm.DOUBLE.equals(type.getTypeName())) {
                        setClass = double.class;
                    } else if (ConstantComm.FLOAT.equals(type.getTypeName())) {
                        setClass = float.class;
                    } else if (ConstantComm.BOOLEAN.equals(type.getTypeName())) {
                        setClass = boolean.class;
                    } else if (ConstantComm.CHAR.equals(type.getTypeName())) {
                        setClass = char.class;
                    } else if (ConstantComm.SHORT.equals(type.getTypeName())) {
                        setClass = short.class;
                    } else if (ConstantComm.BYTE.equals(type.getTypeName())) {
                        setClass = byte.class;
                    } else if (ConstantComm.LONG.equals(type.getTypeName())) {
                        setClass = long.class;
                    } else {
                        setClass = Class.forName(type.getTypeName());
                    }
                }

                setMethod = clazz.getMethod(setMethodName, setClass);

                // 处理引用类型转换问题
                if (!value.getClass().getTypeName().equals(type.getTypeName()) && value.getClass().getTypeName().equals(ConstantComm.OBJECT_STRING)) {
                    String tempValue = (String)value;
                    if (type.getTypeName().equals(ConstantComm.OBJECT_INTEGER) || type.getTypeName().equals(ConstantComm.INT)) {
                        setMethod.invoke(t, Integer.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_FLOAT) || type.getTypeName().equals(ConstantComm.FLOAT)) {
                        setMethod.invoke(t, Float.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_DOUBLE) || type.getTypeName().equals(ConstantComm.DOUBLE)) {
                        setMethod.invoke(t, Double.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_SHORT) || type.getTypeName().equals(ConstantComm.SHORT)) {
                        setMethod.invoke(t, Short.valueOf(tempValue));
                    } else if (type.getTypeName().equals(ConstantComm.OBJECT_LONG) || type.getTypeName().equals(ConstantComm.LONG)) {
                        setMethod.invoke(t, Long.valueOf(tempValue));
                    } else {
                        setMethod.invoke(t, value);
                    }
                } else {
                    setMethod.invoke(t, value);
                }

            } catch (Exception e) {
                ManagerLog.error(e, "input属性传输错误 : " , e.getMessage());
                continue;
            }
        }
    }

    /**
     * @description 输出结果, 固定结果模型
     * @param response servlet返回参数
     * @param obj 结果对象
     * @throws Exception
     * @author ChenglongChu
     * @create 2017/12/14 17:49
    **/
    protected void output(HttpServletResponse response, Object obj) throws Exception {
        // 转String字符串
        String json = JSONObject.toJSONStringWithDateFormat(ManagerResult.newSuccess(obj), ConstantComm.DATE_FORMAT_ONE);
        // 输出流
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    /**
     * @description 输出结果, 不固定结果模型
     * @param response servlet返回参数
     * @param obj 结果对象
     * @throws Exception
     * @author ChenglongChu
     * @create 2017/12/14 17:49
     **/
    protected void commOutput(HttpServletResponse response, Object obj) throws Exception {
        // 转String字符串
        String json = JSONObject.toJSONStringWithDateFormat(obj, ConstantComm.DATE_FORMAT_ONE);
        // 输出流
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    /**
     * @description 实体对象转map
     * @param t 实体对象
     * @throws Exception
     * @author ChenglongChu
     * @create 2017/12/14 17:51
    **/
    protected <T> Map<String, Object> objectToHashMap(T t) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Class<? extends Object> clazz = t.getClass();
        //获取属性集合
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //获取属性名
            String attrName = field.getName();
            //获取get方法名
            String getMethodName = "get" + attrName.substring(0, 1).toUpperCase()+ attrName.substring(1);
            //拿到get方法
            Method getMethod = clazz.getMethod(getMethodName);
            //通过get方法获取属性值放入map中
            map.put(attrName, getMethod.invoke(t));
        }

        return map;
    }

    public static void main(String[] args) {
        // url
        String url = "http://localhost:9090/thirdInterface/selectMcInfo";
        // body参数
        Map<String, Object> params = new HashMap<>();
        params.put("secretKey","GSXEQ11087");
        params.put("thirdIds", Arrays.asList("82BF3226-213D-4794-8259-4B7CC2376B39"));
        // 请求服务拿到返回结果
        ResponseEntity<String> result = httpClient(url, params, HttpMethod.POST);
        System.out.println(result);
    }

    public static ResponseEntity<String> httpClient(String url, Map<String, Object> params, HttpMethod type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json;charset=utf-8");
        String jsonObj = JSONObject.toJSONStringWithDateFormat(params, ConstantComm.DATE_FORMAT_ONE);
        HttpEntity<String> httpEntity=new HttpEntity<>(jsonObj,headers);
        ResponseEntity<String> result= restTemplate.exchange(url, type, httpEntity, String.class);
        return result;
    }
}
