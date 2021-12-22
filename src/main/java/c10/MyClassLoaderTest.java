package c10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader loader = new MyClassLoader();
        System.out.println(loader.getParent());
        Class<?> aClass = loader.loadClass("c10.Hello");
        System.out.println(aClass.getClassLoader());
        Object hello = aClass.newInstance();
        System.out.println(hello);
        Method welcome = aClass.getMethod("welcome");
        String result = (String) welcome.invoke(hello);
        System.out.println(result);
    }
}
