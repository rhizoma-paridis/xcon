package c10;

public class AClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(AClassLoader.class.getClassLoader());
    }
}
