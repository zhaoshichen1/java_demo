package com.mytest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

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
    public void afterReturningTest(JoinPoint jp, Object res){
        System.out.println("[AfterReturning]---模拟记录Log，返回值="+res);
    }

    // 所有当前包的任意类中 public的任意方法都执行
    @Before("execution(public * com.mytest.*.*(..))")
    public void beforeTest(JoinPoint jp){
        System.out.println("[After]---模拟管理事务---");

        Object[] args = jp.getArgs(); // 获取参数信息
        System.out.println("即将打印 "+jp.getSignature().getName()+ "方法的参数信息"); // 获取方法名
        for(Object a: args){
            System.out.println(a);
        }
    }

    @Pointcut("execution(public * com.mytest.UgcService.start(..))")
    private void startExecution(){}

    /**
     * 针对UgcService类中的start方法，定义around逻辑：比如记录方法执行所用的时间
     * @param pjp
     */
    @Around("startExecution()")
    public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
        Date since = new Date();

        // 执行目标方法
        Object result = pjp.proceed();

        Calendar cal = Calendar.getInstance();
        cal.setTime(since);
        long time1 = cal.getTimeInMillis();
        cal.setTime(new Date());
        System.out.println(pjp.getSignature().getName()+"方法执行耗时ms："+(cal.getTimeInMillis()-time1));

        return result;
    }

    @AfterThrowing(value = "startExecution()", throwing="ex")
    public void exceptionTest(JoinPoint jp, Exception ex){
        System.out.println(jp.getSignature().getName()+"方法发生异常："+ex.getMessage());
    }
}
