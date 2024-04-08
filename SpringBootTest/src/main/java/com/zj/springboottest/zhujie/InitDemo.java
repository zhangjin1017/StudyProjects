package com.zj.springboottest.zhujie;

/**
 * @Author zhangjin
 * @Date 2024/3/12 10:57
 * @description:
 */
public class InitDemo {
    @InitMethod
    public void test1() {
        System.out.println("test1");
    }

    public void test2() {
        System.out.println("test2");
    }
    @InitMethod
    public void test3() {
        System.out.println("test3");
    }
}
