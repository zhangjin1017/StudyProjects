package com.zj.seven;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zj.util.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer01 {
    public static final String NORMAL_EXCHANGE = "NORMAL_EXCHANGE";
    public static final String DEAD_EXCHANGE = "DEAD_EXCHANGE";
    public static final String NORMAL_QUEUE = "NORMAL_QUEUE";
    public static final String DEAD_QUEUE = "DEAD_QUEUE";


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMQUtils.getChannel();
        //声明死信和普通交换机 类型为direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);
        //声明死信和普通队列
        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-max-priority",10); //一般由生产者设置
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //正常队列设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","lisi");
        //设置正常队列的长度限制
        arguments.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        //绑定队列和交换机
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");

        DeliverCallback deliverCallback = (consumerTag, message) ->{
            System.out.println("Consumer01接收到的消息是:"+new String(message.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume(NORMAL_QUEUE,true, deliverCallback,consumerTag ->{});




    }



}
