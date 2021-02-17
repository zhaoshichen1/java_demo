/**
 * 人为地制造一个InterruptedException康康
 */
public class SleepInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 100){
                    i++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        t.interrupt();
    }
}


