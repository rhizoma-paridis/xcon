package c27;

import java.util.Map;

public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        
        orderService.order((String) params.get("account"), (Long) params.get("orderId"));
    }
}
