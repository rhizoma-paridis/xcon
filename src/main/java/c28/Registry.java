package c28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> container = new ConcurrentHashMap<>();

    

    public void bind(Object subscriber){
        List<Method> subscriberMethods = getSubscribeMethods(subscriber);
        subscriberMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        ArrayList<Method> methods = new ArrayList<>();
        Class<? extends Object> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                .filter(m -> m.isAnnotationPresent(Subscribe.class)
                    && m.getParameterCount() == 1 
                    && m.getModifiers() == Modifier.PUBLIC)
                .forEach(m -> methods.add(m));
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tierSubscriber(Object subscriber, Method m) {
        String topic = m.getDeclaredAnnotation(Subscribe.class).topic();
        container.computeIfAbsent(topic, t -> new ConcurrentLinkedQueue<>());
        container.get(topic).add(new Subscriber(subscriber, m));
    }

    public void unbind(Object subscriber) {

        container.forEach((topic, subscribers) -> {
            subscribers.forEach(s -> {
                if (s.getSubscriberObject() == subscriber) {
                    s.setDisable(true);
                }
            });
        });
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(String topic) {
        return container.get(topic);
    }

    
}
