package com.zj.forest.test;

import com.dtflys.forest.annotation.*;

import java.util.Map;

public interface ForestClient {

    @Get("http://localhost:9999/hello")
    String helloForest();

    @Get("http://localhost:9999/hello2")
    String hello2Forest(@Query("str") String name);

    //获取token
    @Post(url="http://gw.zf.rdc.finstone.com.cn/oauth/token",
            headers = {
                   "open-appid:message"
            }
    )
    String getToken(@Query Map<String, Object> map);

    @Post(url="http://gw.zf.rdc.finstone.com.cn/api/message/sendmessage",
            contentType = "application/json",
            headers = {
                    "open-appid:message",
                    "Authorization:Bearer {token}"
            }
    )
    String sendMessage(@Var("token") String token, @Body Map<String, Object> map);
}
