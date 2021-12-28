package c19;

public interface FutureService<IN, OUT> {
    // 不需要返回值。
    Future<?> submit(Runnable runnable);

    // 需要返回值
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
