package c27;

import c19.Future;

public interface OrderService {
    Future<String> findOrderDetails(Long orderId);

    /**
     * 提交订单， 没有返回值
     */
    void order(String account, Long orderId);
}
