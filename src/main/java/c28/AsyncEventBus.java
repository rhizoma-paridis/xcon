package c28;

import java.util.concurrent.ThreadPoolExecutor;

public class AsyncEventBus extends EventBus {
    AsyncEventBus(String busName, EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName, exceptionHandler, executor);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        super(busName, null, executor);
    }
}
