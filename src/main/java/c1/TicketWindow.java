package c1;

public class TicketWindow extends Thread {

    private final String name;

    private static final int MAX = 50;

    private static int index = 1;


    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("window: " + name + "current No: " + (index++));
        }
    }

    public static void main(String[] args) {
        /*TicketWindow a = new TicketWindow("a");
        // a.start();
        Thread c = new Thread(a);
        c.start();
        TicketWindow b = new TicketWindow("b");
        b.start();
        // TicketWindow c = new TicketWindow("c");
        // c.start();
        // TicketWindow d = new TicketWindow("d");
        // d.start();*/

        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ff");
    }
}
