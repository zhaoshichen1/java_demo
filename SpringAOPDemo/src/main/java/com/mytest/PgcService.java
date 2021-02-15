package com.mytest;

import org.springframework.stereotype.Service;

@Service
public class PgcService implements MediaService{
    @Override
    public void start() {
        System.out.println("com.mytest.PgcService 初始化");
    }

    @Override
    public void destroy() {
        System.out.println("com.mytest.PgcService 关闭");
    }

    @Override
    public void doSome() {
        System.out.println("com.mytest.PgcService doSome方法");
    }

    @Override
    public void doOther() {
        System.out.println("com.mytest.PgcService doOther方法");
    }
}
