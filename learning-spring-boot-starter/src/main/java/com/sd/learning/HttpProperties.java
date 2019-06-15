package com.sd.learning;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Martin.S
 * @Description
 * @date 2019/6/15 9:50
 */
@ConfigurationProperties(prefix = "http") // 自动获取配置文件中前缀为http的属性，把值传入对象参数
public class HttpProperties {

    // 如果配置文件中配置了http.url属性，则该默认属性会被覆盖
    private String url = "http://www.baidu.com/";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
