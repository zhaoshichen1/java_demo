package SpringDemo;

interface IOCDemo{
    public void doSome();
}

public class SpringIOCDemo implements IOCDemo {
    @Override
    public void doSome() {
        System.out.println("调用方法成功！");
    }
}
