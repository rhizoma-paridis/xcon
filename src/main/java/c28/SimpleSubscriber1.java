package c28;

public class SimpleSubscriber1 {

    @Subscribe
    public void method1(String msg) {
        System.out.println("SimpleSubscriber1.method1()");
        System.out.println("msg = " + msg);
    }

    public static void main(String[] args) {
        EventBus bus = new EventBus("testBus");
        bus.register(new SimpleSubscriber1());
        bus.post("Hello World!");
        System.out.println("Done!");
        bus.post("Hello World!", "test");
    }
}
