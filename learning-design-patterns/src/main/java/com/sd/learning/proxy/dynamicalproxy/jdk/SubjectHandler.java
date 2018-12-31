package com.sd.learning.proxy.dynamicalproxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 抽象主题动态代理
 */
public class SubjectHandler<T> implements InvocationHandler {
    private T target;

    public SubjectHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK代理类中..........调用实际业务逻辑前...参数校验、开启事务...");
        method.invoke(target,args);
        System.out.println("JDK代理类中..........调用实际业务逻辑后...提交事务、关闭资源");
        return null;
    }
}
