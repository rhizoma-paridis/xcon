package c3;

public class FlagThreadExit {

    static class MyTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("start");
            while (!closed && !isInterrupted()) {

            }
            System.out.println("end");
        }

        public void close() {
            closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        myTask.start();

    }
}
