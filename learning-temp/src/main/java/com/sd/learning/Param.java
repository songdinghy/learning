package com.sd.learning;


import com.crt.finfra.servicetopapi.input.SystemLevelPojoInputDTO;
import com.sd.learning.entity.User;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class Param<T1, T2> {

    class A {}
    class B extends A {}

    private Class<T1> entityClass;
    public Param(){
        //Type type = getClass().getGenericSuperclass();
        SystemLevelPojoInputDTO<User> userInput = new SystemLevelPojoInputDTO<>();
        User user = new User();
        user.setName("张三");
        user.setPass("124");
        userInput.setData(user);
        Class<?> clazz = userInput.getClass();
        Type[] type = userInput.getClass().getGenericInterfaces();
        System.out.println("getClass() == " + type[0].getClass());
        System.out.println("type = " + type[0]);
        Type trueType = ((ParameterizedType)type[0]).getActualTypeArguments()[0];
        System.out.println("trueType1 = " + trueType);
        //trueType = ((ParameterizedType)type[0]).getActualTypeArguments()[1];
        //System.out.println("trueType2 = " + trueType);
        //this.entityClass = (Class<T1>)trueType;
        System.out.println("entityClass = " + entityClass);

        B t = new B();
        Type type1 = t.getClass().getGenericSuperclass();

        System.out.println("A is B's super class :" + ((ParameterizedType)type1).getActualTypeArguments().length);
    }

}

 class ClassDemo extends Param<MyClass, MyInvoke> {
    public static void main(String[] args) {
        ClassDemo classDemo = new ClassDemo();
    }
}



 class MyClass {

}


 class MyInvoke {

}