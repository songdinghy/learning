package com.sd.learning.proxy.staticproxy;

/**
 * 抽象主题，没具体要求，可以是接口或者抽象类
 */
public interface Subject {
    /**
     * 业务逻辑，可以有多个，具体代理的时候做方法判断区分业务逻辑
     */
    public void request();
}
