package com.sd.learning.proxy.staticproxy;

/**
 * 代理模式：为对象提供一种代理，以控制对这个对象的访问
 */
public class Proxy implements Subject {
    private Subject subject; // 被代理的对象

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("普通静态代理类中..........调用实际业务逻辑前...参数校验、开启事务...");
        subject.request();
        System.out.println("普通静态代理类中..........调用实际业务逻辑后...提交事务、关闭资源");
    }
}
