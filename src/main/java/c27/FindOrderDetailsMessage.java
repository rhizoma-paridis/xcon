package c27;

import java.util.Map;

import c19.Future;

public class FindOrderDetailsMessage extends MethodMessage {

    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture = orderService.findOrderDetails((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activateFuture");
        try {
            String result = realFuture.get();
            activeFuture.finish(result);
        } catch (Exception e) {
            activeFuture.finish(null);
        }
    }

}
