package c28;

import java.util.concurrent.Executor;

public class EventBus implements Bus {
    
    private final String busName;
    private final Registry registry = new Registry();

    private final static String DEFAULT_BUS_NAME = "default";

    private final static String DEFAULT_TOPIC = "default-topic";

    private final Dispatcher dispatcher;
    
    public EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor) {
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
    }

    public EventBus(String busName) {
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(EventExceptionHandler exceptionHandler) {
        this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }
    @Override
    public void register(Object subscriber) {
        registry.bind(subscriber);
    }
    
    @Override
    public void unregister(Object subscriber) {
        registry.unbind(subscriber);
    }
    
    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }
    
    @Override
    public void post(Object event, String topic) {
        dispatcher.dispatch(this, registry, event, topic);
    }
    
    @Override
    public void close() {
        dispatcher.close();
    }
    
    @Override
    public String getBusName() {
        return busName;
    }
}
