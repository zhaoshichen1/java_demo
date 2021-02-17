package com.mytest;

public interface MediaService {
    // 初始化和关闭逻辑
    public void start() throws InterruptedException,ArithmeticException;

    // 业务逻辑
    public int doOther();
}
