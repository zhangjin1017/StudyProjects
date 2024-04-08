package com.zj.springboottest.Test;

/**
 * @Author zhangjin
 * @Date 2024/3/14 15:20
 * @description:
 */
public class Test2 implements jiekou{

    @Override
    public void test() {
        System.out.println("test");
    }

    @Override
    public void test1() {
        System.out.println("test1");
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.test();
        test2.test1();
        System.out.println(jiekou.name);

    }
}
