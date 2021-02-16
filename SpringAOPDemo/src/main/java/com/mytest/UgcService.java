package com.mytest;

import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * 定义一个Service，用于模拟业务类和业务方法
 */
@Service
public class UgcService implements MediaService {
    @Override
    public void start() {
        try {
            Thread.sleep(18);
        } catch( InterruptedException e){
            e.printStackTrace();
            return;
        }
        System.out.println("com.mytest.UgcService 初始化");
    }

    @Override
    public void destroy() {
        System.out.println("com.mytest.UgcService 关闭");
    }

    @Override
    public void doSome(int a) {
        System.out.println("com.mytest.UgcService doSome方法");
    }

    @Override
    public int doOther() {
        System.out.println("com.mytest.UgcService doOther方法");
        return 22;
    }
}