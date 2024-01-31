package com.zj.forest.test;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ForestController {

    @Resource
    private ForestService forestService;
    @Resource
    private ForestClient forestClient;

    @RequestMapping("/hello")
    public void hello() {
        forestService.testClient();
    }

    @RequestMapping("/hello2")
    public void hello2() {
        forestService.testClient2();
    }

    @RequestMapping("/getToken")
    public String getToken() {
        Map<String, Object> map=new HashMap<>();
        map.put("grant_type","client_credentials");
        map.put("client_id","xindun");
        map.put("client_secret","xindun!@#$");

        JSONObject result = JSONUtil.parseObj(forestClient.getToken(map));

        return result.get("access_token", String.class);
    }

    @RequestMapping("/sendMessage")
    public String sendMessage() {
        Map<String, Object> map=new HashMap<>();
        map.put("bizcode","123456");
        map.put("templateCode","xindun");
        List list = new ArrayList<>();
        list.add("sms");
        map.put("channelType",list);

        HashMap<String, Object> vars = new HashMap<>();
        HashMap<String, Object> var = new HashMap<>();
        var.put("content","Forest测试消息");
        vars.put("abc",var);
        map.put("vars",vars);

        HashMap<String, Object> receivers = new HashMap<>();
        HashMap<String, Object> receiver = new HashMap<>();
        receiver.put("sms","17751591450");
        receivers.put("abc",receiver);
        map.put("receivers",receivers);


        String token = this.getToken();
        System.out.println("token = " + token);

        String sendMessage = forestClient.sendMessage(token, map);
        return sendMessage;
    }

}
