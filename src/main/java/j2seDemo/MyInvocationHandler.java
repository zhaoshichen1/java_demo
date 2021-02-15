package j2seDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * 使用JDK的动态代理Demo
 */
public class MyInvocationHandler {
    public static void main(String[] args){

        Target t = new TargetImpl();
        InvocationHandler handler = new DynamicProxy(t);

        // 声明使用DynamicProxy来动态代理Target接口
        // 注意这里一定得是接口，类就不行了：是newProxyInstance方法规定的。。
        Target proxy = (Target) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), handler);

        // 通过proxy对象来调用方法
        proxy.doSome();
        proxy.doOther();
    }
}

/**
 * 目标类，模拟实现一些业务逻辑
 */
interface Target{
    public void doSome();
    public void doOther();
}

class TargetImpl implements Target{
    public void doSome(){
        System.out.println("Just do some");
    }

    public void doOther(){
        System.out.println("Just do other");
    }
}

/**
 * 模拟实现一个工具类，用于记录日志和记录方法执行耗时
 */
class UtilClass{
    public static void doLog(){
        System.out.println("模拟记录日志");
    }

    public static void recordTime(Date since){
        System.out.println("方法执行耗时："+new Date().compareTo(since));
    }
}

/**
 * 动态代理，实现了InvocationHandler接口
 */
class DynamicProxy implements InvocationHandler{

    private Object target;

    public DynamicProxy(Target t){
        this.target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法的前后增加通用逻辑的调用
        UtilClass.doLog();
        Date since = new Date();

        // 调用目标方法
        method.invoke(this.target, args);

        UtilClass.recordTime(since);

        return null;
    }
}