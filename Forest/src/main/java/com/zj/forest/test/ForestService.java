package com.zj.forest.test;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ForestService {
    
    // 注入自定义的 Forest 接口实例
    @Resource
    private ForestClient forestClient;

    public void testClient() {
        // 调用自定义的 Forest 接口方法
        // 等价于发送 HTTP 请求，请求地址和参数即为 helloForest 方法上注解所标识的内容
        String result = forestClient.helloForest();
        // result 即为 HTTP 请求响应后返回的字符串类型数据
        System.out.println(result);
    }

    public void testClient2() {
        // 调用自定义的 Forest 接口方法
        // 等价于发送 HTTP 请求，请求地址和参数即为 hello2Forest 方法上注解所标识的内容
        String result = forestClient.hello2Forest("Forest");
        // result 即为 HTTP 请求响应后返回的字符串类型数据
        System.out.println(result);
    }

}
