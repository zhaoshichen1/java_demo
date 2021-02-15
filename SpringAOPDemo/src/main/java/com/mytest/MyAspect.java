package com.mytest;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 定义一个切面类，用于定义切面方法
 */
@Aspect
@Component
public class MyAspect {

    // 所有当前包的任意类中 public的doXXX方法都执行
    @Before("execution(public * com.mytest.*.do*(..))")
    public void doLog(){
        System.out.println("---模拟记录Log");
    }
}
