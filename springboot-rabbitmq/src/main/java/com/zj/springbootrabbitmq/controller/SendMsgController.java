package com.zj.springbootrabbitmq.controller;

import com.zj.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //开始发消息
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message){
        log.info("当前时间:{},发送一条信息给两个TTL队列：{}",new Date().toString(),message);
        rabbitTemplate.convertAndSend("X","XA","消息来自ttl为10s的队列:"+message);
        rabbitTemplate.convertAndSend("X","XB","消息来自ttl为40s的队列:"+message);
    }

    @GetMapping("/sendExpirationMsg/{ttlTime}/{message}")
    public void sendMsg(@PathVariable("ttlTime") String ttlTime,@PathVariable("message") String message){
        log.info("当前时间:{},发送一条时长为{}毫秒的信息给QC队列：{}",new Date().toString(),ttlTime,message);
        rabbitTemplate.convertAndSend("X","XC",message,msg -> {
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }

    //开始发消息，基于插件的 消息及 延迟的时间
    @GetMapping("/sendDelayMsg/{delayTime}/{message}")
    public void sendMsg(@PathVariable("message") String message,
                        @PathVariable("delayTime") Integer delayTime){
        log.info("当前时间:{},发送一条时长是{}毫秒TTL信息给延迟队列delayed.queue：{}",
                new Date().toString(),delayTime,message);

        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,
                DelayedQueueConfig.DELAYED_ROUTING_KEY,message, msg -> {
                    //发送消息的时候的延迟时长 单位ms
                    msg.getMessageProperties().setDelay(delayTime);
                    return msg;
                });
    }

}
