package com.zj.springboottest.zhujie;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author zhangjin
 * @Date 2024/3/12 10:44
 * @description:
 */
public class Test {
    public static void main(String[] args) throws Exception {
        // 获取类的所有方法
        Class<?> clazz = Class.forName("com.zj.springboottest.zhujie.InitDemo");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            // 判断方法上是否有InitMethod注解
            boolean annotationPresent = method.isAnnotationPresent(InitMethod.class);
                System.out.println(method.getName()+":"+annotationPresent);
                if (annotationPresent) {
                    method.invoke(clazz.getConstructor(null).newInstance(null), null);
                }
        }
    }


}
