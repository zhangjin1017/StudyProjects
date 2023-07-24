package com.zj.springboottest.celuemodel;

import org.springframework.stereotype.Component;

@Component(value = "email")
public class SendEmail implements SendService {

    @Override
    public void send() {
        System.out.println("send email");
    }
}
