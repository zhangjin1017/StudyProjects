package com.zj.springboottest.Test;

/**
 * @Author zhangjin
 * @Date 2024/3/14 15:15
 * @description:
 */
public class Test extends Abstract {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
        test.test1();
        System.out.println(test.name);



    }

    @Override
    void test1() {
        System.out.println("test1");
    }
}
