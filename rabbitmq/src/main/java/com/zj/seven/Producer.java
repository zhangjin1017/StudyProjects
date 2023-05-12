package com.zj.seven;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.zj.util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/25  19:13
 * desc:死信队列之生产者
 */
public class Producer {
    //普通交换机的名称
    public static final String NORMAL_EXCHANGE = "NORMAL_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //死信消息 设置ttl时间 live to time 单位是ms
//        AMQP.BasicProperties properties =
//                new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 1; i <11 ; i++) {
            String message = "info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes());
        }
    }
}
