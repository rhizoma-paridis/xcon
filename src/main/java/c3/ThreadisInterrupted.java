package c3;

import java.util.concurrent.TimeUnit;

public class ThreadisInterrupted {
    public static void main(String[] args) throws InterruptedException {
        t();
    }

    public static void tIsInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("is interrupted: " + thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("is interrupted: " + thread.isInterrupted());
    }

    public static void tInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.interrupted());
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }

    public static void t() {
        System.out.println("main is interrupted: " + Thread.interrupted());
        Thread.currentThread().interrupt();
        Thread.currentThread().interrupt();
        System.out.println("main is interrupted: " + Thread.currentThread().isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }

    public static void stopThread() {
        new Thread(() -> {
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("thread done.");
        });
    }

}
