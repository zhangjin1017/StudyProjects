package com.zj.springboottest.celuemodel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SendTest {
    @Resource
    private SendFactory sendFactory;

    @Test
    public void test2() {
        String type = "email";
        sendFactory.getSendIns(type).send();
    }
}
