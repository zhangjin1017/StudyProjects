package com.zj.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.zj.util.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task02 {
    //队列名称
    public static final String TASK_QUEUE_NAME = "ACK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();
        //开启发布确认
        channel.confirmSelect();
        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        //在控制台中输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息:"+ message);
        }

    }
}
