public class HelloWorld {
    public static void main(String args[]){
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new ExceptionRunnable());
        t2.setDaemon(true);
        t1.start();
        t2.start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < 200; x++) {
            System.out.print(x+" ");
        }
    }
}

class ExceptionRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("守护线程输出");
        int a = 1/0;
        System.out.println("守护线程异常");
    }
}