package com.sd.learning;

import com.alibaba.fastjson.JSON;
import com.crt.finfra.servicetopapi.input.SystemLevelListInputDTO;
import com.crt.finfra.servicetopapi.input.SystemLevelPojoInputDTO;
import com.sd.learning.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 包含泛型的json转对象
 */
public class JSONGenericReverse {

    public static void main(String[] args) throws IllegalAccessException {
        //convert2();
        //Class<?> clazz = getActualTypeArgument(new SystemLevelPojoInputDTO<User>().getClass());
        //System.out.println("clazz = [" + clazz + "]");

        boolean flag = isPrimitive(new User());
        System.out.println("flag = [" + flag + "]");


        SystemLevelListInputDTO<User> userInput = new SystemLevelListInputDTO<>();
        userInput.setSysId("111");
        User user = new User();
        user.setName("张三");
        user.setPass("124");

        List<User> list = new ArrayList<>();
        list.add(user);

        userInput.setData(list);


        SystemLevelPojoInputDTO<User> userInput2 = new SystemLevelPojoInputDTO<>();
        userInput2.setData(user);
        convert0(userInput2);
        System.out.println("-----bbb---" + user.getName());

    }

    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    private static boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }


    public static void convert0(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return;
        }
        Class<?> clazz = obj.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {//向上循环  遍历父类
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldObj = field.get(obj);
                if (isPrimitive(fieldObj) || fieldObj instanceof  String) {
                    if ("name".equals(field.getName())) {
                        field.set(obj, "李四");
                        System.out.println(field.getName() + "------aaa-----" + field.get(obj));
                    }
                } else if(fieldObj instanceof  List || fieldObj instanceof Set) {
                    Collection collection = (Collection)fieldObj;
                    for (Object listObj : collection) {
                        convert0(listObj);
                    }
                } else {
                    convert0(fieldObj);
                }

            }
        }
    }


    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                entitiClass = (Class<?>) actualTypeArguments[0];
            }
        }

        return entitiClass;
    }


    public static void convert2() {
        SystemLevelPojoInputDTO<User> userInput = new SystemLevelPojoInputDTO<>();
        User user = new User();
        user.setName("张三");
        user.setPass("124");
        userInput.setData(user);

        Object obj = userInput;
        String jsonStr = JSON.toJSONString(obj);
        System.out.println("jsonStr = [" + jsonStr + "]");

        obj = JSON.parseObject(jsonStr, obj.getClass());

        userInput = (SystemLevelPojoInputDTO<User>) obj;
        System.out.println("obj = [" + userInput.getData() + "]");


    }

    public static void convert1() {
        List<List<User>> plist = new ArrayList<>();
        List<User> list = new ArrayList<>();
        plist.add(list);

        User user = new User();
        user.setName("张三");
        user.setPass("124");
        list.add(user);

        Object obj = plist;
        String jsonStr = JSON.toJSONString(obj);
        System.out.println("jsonStr = [" + jsonStr + "]");

        obj = JSON.parseObject(jsonStr, obj.getClass());

        plist = (List<List<User>>) obj;
        System.out.println("obj = [" + obj + "]");
    }
}
