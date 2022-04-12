package c21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TLTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.shutdown();

    }

    public static void t() {
        ThreadLocal<Object> tl = ThreadLocal.withInitial(Object::new);
    }
}
