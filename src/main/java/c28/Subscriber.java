package c28;

import java.lang.reflect.Method;

public class Subscriber {

    private final Object subscribeObject;

    private final Method subscribeMethod;

    private boolean disable = false;

    public Subscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscriberObject() {
        return subscribeObject;
    }

    public void setDisable(boolean b) {
        this.disable = b;
    }

    public boolean isDisable() {
        return disable;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

}
