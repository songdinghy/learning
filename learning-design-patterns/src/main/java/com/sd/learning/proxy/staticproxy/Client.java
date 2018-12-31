package com.sd.learning.proxy.staticproxy;

/**
 * 客户端测试类
 */
public class Client {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }

}
