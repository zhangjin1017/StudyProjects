package com.zj.springboottest.Test;

/**
 * @Author zhangjin
 * @Date 2024/3/14 15:19
 * @description:
 */
public interface jiekou {
    String name="zhangjin";
    void test();
    void test1();

    default void test2(){
        System.out.println("test2");
    }

}
