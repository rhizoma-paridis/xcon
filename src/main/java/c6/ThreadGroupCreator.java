package c6;

public class ThreadGroupCreator {

    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup g1 = new ThreadGroup("g1");
        System.out.println(g1.getParent() == currentGroup);

        ThreadGroup g2 = new ThreadGroup(g1, "g2");
        System.out.println(g2.getParent() == g1);
    }
}
