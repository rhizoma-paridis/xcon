package c27;

public final class OrderServiceFactory {
    private final static ActiveMessageQueue queue = new ActiveMessageQueue();

    private OrderServiceFactory() {
    }

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, queue);
    }


    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderServiceImpl();
        OrderService activeObject = toActiveObject(orderService);
        activeObject.order("account", 1L);
        System.out.println("aa");
        Thread.currentThread().join();
    }
}