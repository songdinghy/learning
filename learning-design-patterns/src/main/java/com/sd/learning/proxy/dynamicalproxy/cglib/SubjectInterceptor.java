package com.sd.learning.proxy.dynamicalproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SubjectInterceptor<T> implements MethodInterceptor {

    private T target;

    public SubjectInterceptor(T target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB代理类中..........调用实际业务逻辑前...参数校验、开启事务...");
        method.invoke(target,objects);
        System.out.println("\"CGLIB代理类中..........调用实际业务逻辑后...提交事务、关闭资源");
        return null;
    }
}
