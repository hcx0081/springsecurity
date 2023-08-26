package com.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * {@code @Description:}
 */
@RestController
public class HelloController {
    @RequestMapping("/index")
    public String index(List<String> list) {
        return "index";
    }
    
    @RequestMapping("/form")
    public String form() {
        return "form";
    }
}