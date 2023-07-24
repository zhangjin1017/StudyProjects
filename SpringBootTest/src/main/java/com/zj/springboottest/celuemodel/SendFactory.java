package com.zj.springboottest.celuemodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SendFactory{
    /*
     * 初始化的时候将所有的ReportService自动加载到Map中
     */
    @Autowired
    private final Map<String, SendService> sendIns = new ConcurrentHashMap<>();

    public SendService getSendIns(String code) {
        SendService sendInstance = sendIns.get(code);
        if (sendInstance == null) {
            throw new RuntimeException("未定义的sendInstance");
        }
        return sendInstance;
    }

}
