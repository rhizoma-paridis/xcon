package c21;

public class TLTest {

    public static void main(String[] args) {

    }

    public static void t() {
        ThreadLocal<Object> tl = ThreadLocal.withInitial(Object::new);
    }
}
