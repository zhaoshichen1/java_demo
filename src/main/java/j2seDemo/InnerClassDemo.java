package j2seDemo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class InnerClassDemo {
    public static void main(String args[]){

        // 普通内部类，需要通过外部类的对象才能实例化内部类
        new OuterClass1().new InnerClass().showNum();

        // private内部类，无法直接实例化内部类，只能通过外部类定义的方法间接地调用内部类的成员方法
        new OuterClass2().useInner();

        // static内部类，直接通过类名初始化实例
        new OuterClass3.InnerClass();

        // 外部类和内部类变量名重名，用不同的this指定
        new OuterClass4().new InnerClass().showNum();

        // 匿名内部类，直接用new Comparator实现了一个interface或者抽象类
        PriorityQueue<Integer> q1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

    }
}

/**
 * 演示普通内部类的成员方法的调用：new OuterClass1().new InnerClass().showNum();
 */
class OuterClass1 {
    private int testNum = 1;
    class InnerClass{
        public void showNum(){
            System.out.println(testNum);
        }
    }
}

/**
 * 演示private内部类的成员方法的调用，只能通过外部类定义的方法才能够间接地访问到
 */
class OuterClass2 {
    private int testNum = 1;
    private class InnerClass{
        public void showNum(){
            System.out.println(testNum);
        }
    }
    public void useInner(){
        new InnerClass().showNum();
    }
}

/**
 * 演示static内部类的成员方法的调用：new OuterClass3.InnerClass();
 */
class OuterClass3 {
    private static int testNum = 1;
    static class InnerClass{
        public void showNum(){
            System.out.println(testNum);
        }
    }
}

/**
 * 演示内部和外部成员变量同名的调用方式：OuterClass4.this和this
 */
class OuterClass4 {
    private int testNum = 1;
    class InnerClass{
        int testNum = 2;
        public void showNum(){
            System.out.println(this.testNum);
            System.out.println(OuterClass4.this.testNum);
        }
    }
}