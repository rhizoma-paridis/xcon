package c26;

import lombok.val;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {

    private final ProductionChannel channel;

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public Worker(String workerName, ProductionChannel channel) {
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Production production = channel.tackProduction();
                System.out.println(Thread.currentThread().getName() + " process " + production);
                production.create();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
