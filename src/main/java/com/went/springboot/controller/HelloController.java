package com.went.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author went
 * @version 1.0
 * @date 2022/5/10 18:37
 */

@Controller
@ResponseBody
//@EnableConfigurationProperties(Car.class)

public class HelloController {


    @RequestMapping("/hello")
    public String helloworld(){
        return "hello world!";
    }




}
