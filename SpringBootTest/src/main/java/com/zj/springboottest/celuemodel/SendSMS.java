package com.zj.springboottest.celuemodel;

import org.springframework.stereotype.Component;

@Component(value = "sms")
public class SendSMS implements SendService {


    @Override
    public void send() {
        System.out.println("send sms");
    }
}
