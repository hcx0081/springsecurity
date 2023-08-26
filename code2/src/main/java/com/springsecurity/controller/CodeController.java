package com.springsecurity.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * {@code @Description:}
 */
@Controller
@RequestMapping("/kaptcha")
public class CodeController {
    @Resource
    private Kaptcha kaptcha;
    
    @GetMapping("/code")
    public void code(HttpSession httpSession) {
        String code = kaptcha.render();
        httpSession.setAttribute("code", code);
    }
}