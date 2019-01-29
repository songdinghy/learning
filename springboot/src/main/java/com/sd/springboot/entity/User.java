package com.sd.springboot.entity;

import com.sd.springboot.Groups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class User {
    @NotBlank(message = "ID不能为空",groups = {Groups.Add.class, Groups.Update.class})
    private String id;
    @NotNull
    @Length(max = 20)
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z][0-9]",message = "密码格式不对")
    private String pass;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTime;
    @Max(10)
    @Min(1)
    private Integer level;

}


