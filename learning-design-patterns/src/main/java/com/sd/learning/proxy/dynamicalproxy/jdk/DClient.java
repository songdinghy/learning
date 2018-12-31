package com.sd.learning.proxy.dynamicalproxy.jdk;

import com.sd.learning.proxy.staticproxy.RealSubject;
import com.sd.learning.proxy.staticproxy.Subject;

import java.lang.reflect.Proxy;

public class DClient {
    public static void main(String[] args) {
       Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), RealSubject.class.getInterfaces(),new SubjectHandler<Subject>(new RealSubject()));
       subject.request();

    }
}
