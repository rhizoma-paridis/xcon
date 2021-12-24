package c16;

import c4.Mutex;

public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int writingWriters = 0;

    private int waitingWriters = 0;

    private int readingReaders = 0;

    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock writeLock() {
        return null;
    }

    void incrementWritingWriters() {
        writingWriters++;
    }

    void incrementWaitingWriters() {
        waitingWriters++;
    }

    void incrementReadingReaders() {

        readingReaders++;
    }

    void decrementWritingWriters() {
        writingWriters--;
    }

    void decrementWaitingWriters() {
        waitingWriters--;
    }

    void decrementReadingReaders() {

        readingReaders--;
    }
    @Override
    public int getWritingWriters() {
        return writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    Object getMutex() {
        return MUTEX;
    }

    boolean getPreferWriter() {
        return preferWriter;
    }

    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
