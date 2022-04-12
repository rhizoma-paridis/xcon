package c27;

public class ActivateDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActivateDaemonThread(ActiveMessageQueue queue) {
        super("ActivateDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            MethodMessage message = queue.take();
            message.execute();
        }
    }

}
