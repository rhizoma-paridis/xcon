package c1;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static void main(String[] args) {
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();
    }

    private static void browseNews() {
        while (true) {
            System.out.println("haha");
            sleep(1);
        }
    }

    private static void enjoyMusic() {
        while (true) {
            System.out.println("music");
            sleep(1);
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
