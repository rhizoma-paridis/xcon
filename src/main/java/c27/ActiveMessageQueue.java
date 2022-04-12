package c27;

import java.util.LinkedList;

public class ActiveMessageQueue {

    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActivateDaemonThread(this).start();
    }

    protected MethodMessage take() {
        synchronized (this) {
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst();
        }
    }

    public void offer(MethodMessage message) {
        synchronized (this) {
            messages.addLast(message);
            this.notify();
        }
    }
}
