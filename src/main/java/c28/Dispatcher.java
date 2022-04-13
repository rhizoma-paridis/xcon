package c28;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Dispatcher {

    private final Executor executorService;

    private final EventExceptionHandler exceptionHandler;

    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_THREAD_EXECUTOR_SERVICE = PreThreadExecutorService.INSTANCE;

    private Dispatcher(EventExceptionHandler exceptionHandler, Executor executorService) {
        this.exceptionHandler = exceptionHandler;
        this.executorService = executorService;
    }

    public void dispatch(EventBus eventBus, Registry registry, Object event, String topic) {
        ConcurrentLinkedQueue<Subscriber> scanSubscribers = registry.scanSubscriber(topic);
        if (null == scanSubscribers) {
            if (exceptionHandler != null) {
                exceptionHandler.handle(new IllegalArgumentException("No subscriber for topic: " + topic),
                        new BaseEventContext(eventBus.getBusName(), null, event));
            }
            return;
        }

        scanSubscribers.stream()
                .filter(s -> !s.isDisable())
                .filter(s -> {
                    Method subscribeMethod = s.getSubscribeMethod();
                    return subscribeMethod.getParameterTypes()[0].isAssignableFrom(event.getClass());
                }).forEach(s -> realInvokeSubscribe(s, event, eventBus));
    }

    private void realInvokeSubscribe(Subscriber s, Object event, EventBus eventBus) {
        Method subscribeMethod = s.getSubscribeMethod();
        Object subscriberObject = s.getSubscriberObject();
        executorService.execute(() -> {
            try {
                subscribeMethod.invoke(subscriberObject, event);
            } catch (Exception e) {
                if (exceptionHandler != null) {
                    exceptionHandler.handle(e, new BaseEventContext(eventBus.getBusName(), s, event));
                }
            }
        });

    }

    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    public static Dispatcher newDispatcher(EventExceptionHandler exceptionHandler, Executor executor) {
        return new Dispatcher(exceptionHandler, executor);
    }

    static Dispatcher seqDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(exceptionHandler, SEQ_EXECUTOR_SERVICE);
    }

    static Dispatcher perTheadDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(exceptionHandler, PRE_THREAD_EXECUTOR_SERVICE);
    }


    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PreThreadExecutorService implements Executor {

        private static final PreThreadExecutorService INSTANCE = new PreThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseEventContext implements EventContext{

        private final String busName;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String busName, Subscriber subscriber, Object event) {
            this.busName = busName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return null;
        }

        @Override
        public Subscriber getSubscriber() {
            return subscriber;
        }

        @Override
        public Object getEvent() {
            return event;
        }

        @Override
        public Method getMethod() {
            return null;
        }
    }
}
