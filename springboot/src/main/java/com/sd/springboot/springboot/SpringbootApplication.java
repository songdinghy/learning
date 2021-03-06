package com.sd.springboot.springboot;

import com.sd.learning.HttpClient;
import com.sd.springboot.Groups;
import com.sd.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HttpClient httpClient;

    @RequestMapping("index")
    @ResponseBody
    public String index(@RequestBody @Validated(Groups.Select.class) User user){

        System.out.println("user = [" + user + "]");
        return httpClient.getHtml();
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(@RequestBody @Validated(Groups.Add.class) User user){
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

