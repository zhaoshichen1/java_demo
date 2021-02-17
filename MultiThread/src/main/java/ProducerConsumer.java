import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumer {

    public static void main(String[] args){
        List<Integer> stock = new ArrayList<>();
        Thread t1 = new Thread(new Producer(5,stock));
        Thread t2 = new Thread(new Consumer(5, stock));
        t1.start();
        t2.start();
    }
}

class Producer implements Runnable {
    private List<Integer> stock;
    private int stockSize;

    public Producer(int size, List<Integer> l){
        this.stockSize = size;
        this.stock = l;
    }

    @Override
    public void run(){
        Random r = new Random();
        while(true){ // 死循环，持续生产
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (stock){
                if(stock.size() >= stockSize){ // 仓库满仓等待逻辑
                    try {
                        System.out.println("[生产]已满仓，进入等待");
                        stock.wait();
                        System.out.println("[生产]仓库有空余位置，继续生产，目前仓储："+stock.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 仓库有空余位置，继续生产
                stock.add(1);
                stock.notifyAll();
                System.out.println("[生产]+1,现有："+stock.size());
            }
        }
    }
}


class Consumer implements Runnable {
    private List<Integer> stock;
    private int stockSize;

    public Consumer(int size, List<Integer> l){
        this.stockSize = size;
        this.stock = l;
    }

    @Override
    public void run(){
        Random r = new Random();
        while(true){ // 死循环，持续消费
            try {
                Thread.sleep(r.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (stock){
                if(stock.size() <= 0){ // 仓库空了，进入等待状态
                    System.out.println("[消费]仓库无货，进入等待");
                    try {
                        stock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[消费]仓库有货，继续消费，现有："+stock.size());
                }
                // 仓库有货，消费
                stock.remove(0);
                stock.notifyAll();
                System.out.println("[消费]仓库有货，消费-1，现有："+stock.size());
            }
        }
    }
}



