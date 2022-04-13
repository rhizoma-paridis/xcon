package c28;

import java.lang.reflect.Method;

public interface EventContext {

    String getSource();

    Object getSubscriber();

    Object getEvent();

    Method getMethod();
}
