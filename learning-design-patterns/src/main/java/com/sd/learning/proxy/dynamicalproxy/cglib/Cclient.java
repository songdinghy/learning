package com.sd.learning.proxy.dynamicalproxy.cglib;

import com.sd.learning.proxy.staticproxy.RealSubject;
import net.sf.cglib.proxy.Enhancer;

public class Cclient {

    public static void main(String[] args) {



        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(RealSubject.class);
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(new SubjectInterceptor<RealSubject>(new RealSubject()));
        RealSubject realSubject = (RealSubject) enhancer.create();
        realSubject.request();

    }
}
