package com.zj.springboottest;

import com.alipay.sofa.runtime.api.annotation.SofaAsyncInit;
import com.zj.springboottest.aop.Order;
import com.zj.springboottest.aop.OrderService;
import com.zj.springboottest.sofa.test1;
import com.zj.springboottest.sofa.test2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(SpringBootTestApplication.class).run(args);
    }

    @Autowired
    private OrderService orderService;

    @Bean(initMethod = "init")
    @SofaAsyncInit
    public test1 test1(){
        System.out.println("test1");
        return new test1();
    }
    @Bean(initMethod = "init")
    @SofaAsyncInit
    public test2 test2() {
        System.out.println("test2");
        return new test2();
    }


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
