package com.zj.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zj.util.RabbitMQUtils;
import com.zj.util.SleepUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Work03 {
    public static final String TASK_QUEUE_NAME = "ACK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();
        System.out.println("C1等待接收消息处理时间较短");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            SleepUtils.sleep(1);
            System.out.println("接收到消息:" + new String(message.getBody(), StandardCharsets.UTF_8));
            //手动应答
            /**
             * 1.消息的标记tag
             * 2.是否批量应答未应答消息
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
//        channel.basicQos(1);
        channel.basicQos(2);
        channel.basicConsume(TASK_QUEUE_NAME,false,deliverCallback,(consumerTag)->{
            System.out.println(consumerTag + "消费者1取消消费接口回调逻辑");
        });
    }
}
