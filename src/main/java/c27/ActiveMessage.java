package c27;

import c19.Future;

import java.lang.reflect.Method;

public class ActiveMessage {
    private final Object[] objects;

    private final Method method;

    private final ActiveFuture<Object> future;

    private final Object service;

    public ActiveMessage(Object[] objects, Method method, ActiveFuture<Object> future, Object service) {
        this.objects = objects;
        this.method = method;
        this.future = future;
        this.service = service;
    }

    public void execute() {
        try {
            Object result = method.invoke(service, objects);
            if (future != null) {
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                future.finish(realResult);
            }
        } catch (Exception e) {
            if (future != null) {
                future.finish(null);
            }
        }
    }

    // 使用建造者模式，创建 activeMessage
    public static class Builder {
        private Object[] objects;

        private Method method;

        private ActiveFuture<Object> future;

        private Object service;

        public Builder setObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder setFuture(ActiveFuture<Object> future) {
            this.future = future;
            return this;
        }

        public Builder setService(Object service) {
            this.service = service;
            return this;
        }

        public ActiveMessage build() {
            return new ActiveMessage(objects, method, future, service);
        }
    }

}
