package com.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@code @Description:}
 */
@Controller
public class DataController {
    @RequestMapping("/data")
    public String data() {
        System.out.println(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return "data";
    }
}