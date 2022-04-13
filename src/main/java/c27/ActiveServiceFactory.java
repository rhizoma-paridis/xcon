package c27;

import c19.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActiveServiceFactory {

    private final static ActiveMessageQueue queue = new ActiveMessageQueue();

    public static <T>  T active(T instance) {
        Object o = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new ActiveInvocationHandler(instance));
        return (T) o;
    }

    private static class ActiveInvocationHandler<T> implements InvocationHandler {
        private final T instance;

        ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.setMethod(method).setObjects(args).setService(instance);
                Object result = null;
                if (isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.setFuture((ActiveFuture) result);
                }
                // queue.offer(builder.build());
                return result;
            }
            return null;
        }

        private void checkMethod(Method method) {
            if(!isReturnVoidType(method) && !isReturnFutureType(method)) {
                throw new IllegalArgumentException("Method " + method.getName() + " must return void or Future");
            }
        }

        private boolean isReturnVoidType(Method method) {
            return method.getReturnType().equals(void.class);
        }

        private boolean isReturnFutureType(Method method) {
            return method.getReturnType().equals(Future.class);
        }
    }
}
