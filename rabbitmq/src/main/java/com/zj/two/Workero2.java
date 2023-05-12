package com.zj.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zj.util.RabbitMQUtils;

public class Workero2 {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        // 创建通道
        Channel channel = RabbitMQUtils.getChannel();
        //消息的接受
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息:" + new String(message.getBody()));
        };

        //消息接受被取消时，执行下面的内容
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息被消费者取消消费接口回调逻辑");
        };

        System.out.println("C2等待接收消息....");
        //消息的接受
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
