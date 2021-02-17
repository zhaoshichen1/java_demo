import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreation {
    public static void main(String[] args){
        // 1. 通过继承Thread创建线程
        Thread t1 = new ThreadExtend();

        // 2. 通过实现Runnable接口创建线程：较为常用
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现Runnable接口创建线程");
            }
        });

        // 3. 通过实现Callable接口创建线程并且获取返回值
        Callable call = new Callable() {
            @Override
            public Object call() {
                System.out.println("实现Callable接口，并且有返回值");
                return "test";
            }
        };
        FutureTask futureTask = new FutureTask(call); // 通过futureTask封装callable为Runnable的实现类
        Thread t3 = new Thread(futureTask);

        // 执行线程
        t1.start();
        t2.start();
        t3.start();

        // 注意这里调用get会导致主线程阻塞，是加强版的join
        try {
            System.out.println("t3返回值："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 继承Thread类，重写run方法创建线程
 */
class ThreadExtend extends Thread{
    @Override
    public void run (){
        System.out.println("继承方式创建线程");
    }
}

