package com.zj.springbootrabbitmq.controller;

import com.zj.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/confirm")
@RestController
public class ProductController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //开始发消息,测试确认
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable("message") String message){
        //指定消息 id 为 1
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY,message+"key1",correlationData1);
        log.info("发送消息内容:{}",message+"key1");

        //指定消息 id 为 2
        CorrelationData correlationData2 = new CorrelationData("2");
        String CONFIRM_ROUTING_KEY = "key2";
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                CONFIRM_ROUTING_KEY,message+"key2",correlationData2);
        log.info("发送消息内容:{}",message+"key2");
    }

    @RequestMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message){
        //向testQueue队列

        //向testGroup组发送消息


        log.info("发送消息内容:{}",message);

    }
}
