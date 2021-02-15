package com.mytest;

public interface MediaService {
    // 初始化和关闭逻辑
    public void start();
    public void destroy();

    // 业务逻辑
    public void doSome();
    public void doOther();
}
