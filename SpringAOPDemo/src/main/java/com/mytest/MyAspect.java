package com.mytest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 定义一个切面类，用于定义切面方法
 */
@Aspect
@Component
public class MyAspect {

    /**
     * 所有当前包的任意类中 public的doXXX方法都执行
     * @param res 获取目标方法的返回值并可以进行相应的处理
     */
    @AfterReturning(value = "execution(public * com.mytest.*.do*(..))", returning = "res")
    public void doLog(Object res){
        System.out.println("[AfterReturning]---模拟记录Log，返回值="+res);
        if (res != null){
            res = 88;
            System.out.println("[AfterReturning]--我修改了obj,"+res);
        }
    }

    // 所有当前包的任意类中 public的任意方法都执行
    @Before("execution(public * com.mytest.*.*(..))")
    public void doTrans(JoinPoint jp){
        System.out.println("[After]---模拟管理事务---");

        Object[] args = jp.getArgs(); // 获取参数信息
        System.out.println("即将打印 "+jp.getSignature().getName()+ "方法的参数信息"); // 获取方法名
        for(Object a: args){
            System.out.println(a);
        }
    }
}
