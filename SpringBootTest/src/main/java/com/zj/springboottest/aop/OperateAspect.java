package com.zj.springboottest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author zhangjin
 * @Date 2024/1/18 15:38
 * @description:
 */

@Component
@Aspect
public class OperateAspect {

    @Pointcut("@annotation(RecordOperate)")
    public void pointcut() {
    }

    @Around("pointcut() && args(order)")
    public Object around(ProceedingJoinPoint joinPoint,Order order) throws Throwable {
        Object proceed = joinPoint.proceed();
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                RecordOperate annotation = signature.getMethod().getAnnotation(RecordOperate.class);

                OperateLogDO operateLogDO = new OperateLogDO();
                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setName(order.getName());
                operateLogDO.setResult(proceed.toString());

                System.out.println("insert operateLogDO:" + operateLogDO);
        return proceed;
    }
}
