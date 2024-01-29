package com.zj.springboottest.aop;

import org.springframework.stereotype.Service;

/**
 * @Author zhangjin
 * @Date 2024/1/18 15:25
 * @description:
 */
@Service
public class OrderService {

    @RecordOperate(desc = "saveOrder")
    public Boolean saveOrder(Order order) {
        System.out.println("saveOrder");
        return true;
    }

    @RecordOperate(desc = "updateOrder")
    public Boolean updateOrder(Order order) {
        System.out.println("updateOrder");
        return true;
    }
}
