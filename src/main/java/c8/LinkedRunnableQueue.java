package c8;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnables = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnables) {
            if (runnables.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnables.addLast(runnable);
                runnables.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnables) {
            while (runnables.isEmpty()) {
                try {
                    runnables.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return runnables.removeFirst();
        }
    }

    @Override
    public int size() {
        return 0;
    }
}
