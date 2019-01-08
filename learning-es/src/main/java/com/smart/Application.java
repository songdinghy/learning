package com.smart;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.smart.elasticsearch.blog.domain.Blog;
import com.smart.elasticsearch.blog.service.BlogJestService;
import com.smart.elasticsearch.core.base.JestDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaowenming
 * @create 2017-12-16 21:03
 **/
@SpringBootApplication
@ComponentScan(value = {"com.smart.elasticsearch"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
