package com.sd.springboot.springboot;

import com.sd.springboot.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@SpringBootApplication
@RestController
@Validated
public class SpringbootApplication {

    @RequestMapping("index")
    @ResponseBody
    public String index(@RequestBody @Valid User user){
        System.out.println("user = [" + user + "]");
        return "hello";
    }

    /**
     *  用于测试
     * @param id id数不能小于10 @RequestParam类型的参数需要在Controller上增加@Validated
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String test(@Min(value = 10, message = "id最小只能是10") @RequestParam("id")
                               Integer id){
        return "恭喜你拿到参数了";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}

