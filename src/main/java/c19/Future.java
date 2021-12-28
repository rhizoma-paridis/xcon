package c19;

public interface Future<T> {
    // 返回结果后， 方法阻塞
    T get() throws InterruptedException;
    // 判断任务是否已完成
    boolean done();
}
