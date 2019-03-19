package com.sd.learning.learningjavacore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LearningJavacoreApplication {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(LearningJavacoreApplication.class, args);
    }

}
