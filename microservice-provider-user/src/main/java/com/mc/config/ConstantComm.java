package com.mc.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenglongChu
 * @description 基础常量
 * @create 2017/12/13 14:12
 * @since v0.1
 */
public class ConstantComm {
    // date format
    public static final String DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_TWO = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_THREE = "yyyy-MM-dd";

    // base type
    public static final String INT = "int";
    public static final String DOUBLE = "double";
    public static final String SHORT= "short";
    public static final String LONG = "long";
    public static final String BOOLEAN = "boolean";
    public static final String CHAR = "char";
    public static final String BYTE = "byte";
    public static final String FLOAT = "float";

    // object type
    public static final String OBJECT_INTEGER = "java.lang.Integer";
    public static final String OBJECT_DOUBLE = "java.lang.Double";
    public static final String OBJECT_SHORT= "java.lang.Short";
    public static final String OBJECT_LONG = "java.lang.Long";
    public static final String OBJECT_BOOLEAN = "java.lang.Boolean";
    public static final String OBJECT_CHAR = "java.lang.Char";
    public static final String OBJECT_BYTE = "java.lang.Byte";
    public static final String OBJECT_FLOAT = "java.lang.Float";
    public static final String OBJECT_STRING = "java.lang.String";

    // operation
    public static final String GET = "get";
    public static final String SET = "set";

    // number
    public static final int NUM_ZERO = 0;
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int NUM_FOUR = 4;
    public static final int NUM_FIVE = 5;
    public static final int NUM_SIX = 6;
    public static final int NUM_SEVEN = 7;
    public static final int NUM_EIGHT = 8;
    public static final int NUM_NINE = 9;

    // role
    public static final int DEFAULT_ROLE = 1000000000;

    // data belong
    public static final String NATIVE = "native";
    public static final String OTHER = "other";

    // selector map key
    public static final String STRING_KEY = "key";
    public static final String STRING_VALUE = "value";

    // DEFAULT PARAM
    public static final String DEFAULT_BRIEF = "这个家伙很懒, 什么也没写.";
    public static final String DEFAULT_PHOTO = "../image/nav/default-people-photo.jpg";

    // user status
    public enum USER_STATUS {
        NORMAL("user_status_001","正常"),
        FREEZE("user_status_002","冻结"),
        DORMANT("user_status_003","休眠"),
        DELETE("user_status_004","注销");

        private String key;
        private String value;

        USER_STATUS(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static List<String> getKeys() {
            List<String> keys = new ArrayList<>();
            for (USER_STATUS e : USER_STATUS.values()) {
                keys.add(e.getKey());
            }
            return keys;
        }

        public static List<String> getValues() {
            List<String> names = new ArrayList<>();
            for (USER_STATUS e : USER_STATUS.values()) {
                names.add(e.getValue());
            }
            return names;
        }

        public static List<Map<String, String>> getAll() {
            List<Map<String, String>> eList = new ArrayList<>();
            for (USER_STATUS e : USER_STATUS.values()) {
                Map<String, String> eMap = new HashMap<>();
                eMap.put(STRING_KEY, e.getKey());
                eMap.put(STRING_VALUE, e.getValue());
                eList.add(eMap);
            }
            return eList;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    // password type
    public enum PASSWORD_TYPE {
        LOGIN("password_type_001","登录密码"),QUERY("password_type_002","查询密码"),DEAL("password_type_003","交易密码");

        private String key;
        private String value;

        PASSWORD_TYPE(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static List<String> getKeys() {
            List<String> keys = new ArrayList<>();
            for (PASSWORD_TYPE e : PASSWORD_TYPE.values()) {
                keys.add(e.getKey());
            }
            return keys;
        }

        public static List<String> getValues() {
            List<String> names = new ArrayList<>();
            for (PASSWORD_TYPE e : PASSWORD_TYPE.values()) {
                names.add(e.getValue());
            }
            return names;
        }

        public static List<Map<String, String>> getAll() {
            List<Map<String, String>> eList = new ArrayList<>();
            for (PASSWORD_TYPE e : PASSWORD_TYPE.values()) {
                Map<String, String> eMap = new HashMap<>();
                eMap.put(STRING_KEY, e.getKey());
                eMap.put(STRING_VALUE, e.getValue());
                eList.add(eMap);
            }
            return eList;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
