package c27;

import java.util.concurrent.TimeUnit;

import c19.Future;
import c19.FutureService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Future<String> findOrderDetails(Long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            // 模拟查询订单详情
            System.out.println("查询订单详情：" + input);
            return "orderId: " + input;
        }, orderId);
    }

    @Override
    public void order(String account, Long orderId) {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("下单成功：" + account + "," + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
