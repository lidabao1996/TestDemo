package reflect;

import java.lang.reflect.Method;

/**
 * 2019-05-06
 */
public class Demo1 {
    public static void main(String[] args) {

        try {
            Class<?> clz = Class.forName("reflect.Student");//指定初始化类

            Student student = (Student)clz.newInstance();
            Method a = clz.getMethod("getModifiers");
            System.out.println("a = " + a);


            Method[] methods = clz.getMethods();//获取类中所有的方法包括继承类的方法
            Method[] methods2 = clz.getDeclaredMethods();//获取类中所有的方法包括继承类的方法



            for (Method method:methods2) {
                System.out.println("method.getName() = " + method.getName()+",方法类型"+method.getReturnType().getName());

                System.out.println("method.getModifiers() = " + method.getModifiers());
                System.out.println("method.getParameters() = " + method.getParameters());
                System.out.println("method.getAnnotations() = " + method.getAnnotations());

            }

            System.out.println("继承的父类clz.getSupercl" +
                    "ass() = " + clz.getSuperclass());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
