package c1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // new Thread(TryConcurrency::enjoyMusic).start();
        // browseNews();
        Method method = TryConcurrency.class.getMethod("browseNews");
        Class<?> type = method.getReturnType();
        System.out.println(type);
        Object o = method.invoke(new TryConcurrency());
        System.out.println(o);
    }

    public void browseNews() {
        // while (true) {
            System.out.println("haha");
            // sleep(1);
        // }
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
