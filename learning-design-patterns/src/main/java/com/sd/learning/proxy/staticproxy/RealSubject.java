package com.sd.learning.proxy.staticproxy;

/**
 * 真实主题，被代理的对象
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("我是真正干活的");
    }
}
