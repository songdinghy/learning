package com.sd.learning.entity;

import com.crt.finfra.servicetopapi.input.internal.InputData;
import lombok.Data;

@Data
public class User implements InputData {
    private static final long serialVersionUID = -8493097592968121669L;
    private  String name;
    private  String pass;

}
