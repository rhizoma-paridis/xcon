package c16;

public interface Lock {

    void lock() throws InterruptedException;

    void unlock();

}
