package com.zj.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    public static Channel getChannel() throws IOException, TimeoutException {

        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP 连接RabbitMQ对列
        factory.setHost("43.140.252.215");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("admin");

        //创建连接
        Connection connection = factory.newConnection();
        //获取信道

        return connection.createChannel();
    }
}
