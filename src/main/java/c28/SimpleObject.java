package c28;

public class SimpleObject {

    @Subscribe(topic = "alex-topic")
    public void test2() {
        System.out.println("test2");
    }

    @Subscribe(topic = "test-topic")
    public void test3() {
        System.out.println("test3");
    }
}
