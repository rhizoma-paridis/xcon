package c5;

import java.util.LinkedList;

public class EventQueue {
    private final int MAX;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.MAX = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= MAX) {
                try {
                    console("the queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            eventQueue.notify();
            console("the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
