package c8;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) {
        BasicThreadPool pool = new BasicThreadPool(2, 6, 4, 100);

        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " running");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        while (true) {
            System.out.println("activeCount: " + pool.getActiveCount());
            System.out.println("queueSize: " + pool.getQueueSize());
            System.out.println("coreSize: " + pool.getCoreSize());
            System.out.println("maxSize: " + pool.getMaxSize());
            System.out.println("=========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
