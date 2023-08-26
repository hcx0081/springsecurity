package com.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @Description:}
 */
@RestController
public class CorsController {
    @RequestMapping("/cors")
    public String cors() {
        System.out.println("cors");
        return "cors";
    }
}