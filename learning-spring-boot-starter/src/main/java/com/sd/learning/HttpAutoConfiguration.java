package com.sd.learning;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Martin.S
 * @Description
 * @date 2019/6/15 9:53
 */
@Configuration
@EnableConfigurationProperties(HttpProperties.class)
public class HttpAutoConfiguration {

    @Resource
    private HttpProperties properties; // 使用配置

    // 在Spring上下文中创建一个对象
    @Bean
    @ConditionalOnMissingBean
    public HttpClient init() {
        HttpClient client = new HttpClient();

        String url = properties.getUrl();
        client.setUrl(url);
        return client;
    }

}

