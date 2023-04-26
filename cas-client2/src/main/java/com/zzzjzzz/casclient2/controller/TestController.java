package com.zzzjzzz.casclient2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "word2";
    }

    //登出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:http://localhost:8080/cas_overlay/logout";
    }
}
