package c15;

public class ObservableThread<T> extends Thread implements Observable{

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("task is required");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            update(Cycle.RUNNING, null, null);
            T result = task.call();
            update(Cycle.DONE, result, null);
        } catch (Exception e) {
            update(Cycle.ERROR, null , e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    lifecycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception exception) {
            if (cycle == Cycle.ERROR) {
                throw exception;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return cycle;
    }
}
