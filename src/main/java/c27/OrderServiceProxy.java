package c27;

import java.util.HashMap;
import java.util.Map;

import c19.Future;

public class OrderServiceProxy implements OrderService {

    private final OrderService orderService;
    private final ActiveMessageQueue activeMessageQueue;;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(Long orderId) {
        final ActiveFuture<String> activeFuture = new ActiveFuture();
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("activateFuture", activeFuture);
        MethodMessage message = new FindOrderDetailsMessage(params, orderService);
        activeMessageQueue.offer(message);
        return activeFuture;
    }

    @Override
    public void order(String account, Long orderId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("account", account);
        params.put("orderId", orderId);
        MethodMessage message = new OrderMessage(params, orderService);
        activeMessageQueue.offer(message);
    }

    
}
