package c29;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher implements DynamicRouter<Message> {


    private final Map<Class<? extends Message>, Channel> routeTable;


    public EventDispatcher() {
        this.routeTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routeTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routeTable.containsKey(message.getType())) {
            routeTable.get(message.getType()).dispatch(message);
        } else {
            throw new IllegalArgumentException("No channel registered for message type: " + message.getType());
        }

    }
}
