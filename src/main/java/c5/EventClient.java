package c5;

import java.util.concurrent.TimeUnit;

public class EventClient {

    public static void main(String[] args) {
        EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}
