package com.zj.springboottest;

import com.zj.springboottest.aop.Order;
import com.zj.springboottest.aop.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(SpringBootTestApplication.class).run(args);
    }

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) {
        Order saveOrder = new Order();
        saveOrder.setId(1);
        saveOrder.setName("11");
        orderService.saveOrder(saveOrder);

        Order updateOrder = new Order();
        updateOrder.setId(2);
        updateOrder.setName("22");
        orderService.updateOrder(updateOrder);
    }

}
