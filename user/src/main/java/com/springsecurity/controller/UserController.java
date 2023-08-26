package com.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @Description:}
 */
@RestController
public class UserController {
    @RequestMapping("/hello")
    public Authentication hello() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}