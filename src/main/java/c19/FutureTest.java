package c19;

import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) {
        // noResult();
        hasResult();
    }


    public static void noResult() {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done.");
        });
        try {
            System.out.println("before get");
            future.get();
            System.out.println("after get");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hasResult() {
        FutureService<String, Integer> service = FutureService.newService();
        Future<Integer> future = service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "hello");
        try {
            System.out.println("before get");
            System.out.println(future.get());
            System.out.println("after get");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
