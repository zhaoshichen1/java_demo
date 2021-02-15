package j2seDemo;

import com.sun.tools.javac.jvm.Code;

import java.util.*;

public class CodeBlockDemo {
    static String country;
    List<Integer> children;

    // 静态代码块
    static {
        System.out.println("静态代码块 -> 初始化静态属性");
        country = "中国";
    }    // 构造代码块

    {
        System.out.println("构造代码块 -> 初始化对象实例");
        this.children = new ArrayList<>();
    }

    // 构造方法
    CodeBlockDemo() {
        System.out.println("构造方法");
    }

    // main方法
    public static void main(String args[]) {
        System.out.println("Main方法");
        new CodeBlockDemo();
    }
}