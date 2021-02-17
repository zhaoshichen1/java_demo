package com.mytest;

import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * 定义一个Service，用于模拟业务类和业务方法
 */
@Service
public class UgcService implements MediaService {
    @Override
    public void start() throws InterruptedException,ArithmeticException {
        Thread.sleep(18);
        int a = 3/0;
        System.out.println("com.mytest.UgcService 初始化");
    }

    @Override
    public int doOther() {
        System.out.println("com.mytest.UgcService doOther方法");
        return 22;
    }
}