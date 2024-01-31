package com.zj.springboottest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangjin
 * @Date 2024/1/29 13:47
 * @description:
 */
@RestController
public class ForestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Forest!";
    }

    @RequestMapping("/hello2")
    public String hello2(String str) {
        return "Hello2:"+str;
    }

    @RequestMapping("/hello3")
    public String hello3(String str,String str2) {
        return "Hello3:"+str+","+str2;
    }

}
