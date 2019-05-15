package reflect;

import java.lang.reflect.Method;

public class Demo4 {
    public static void main(String[] args) {

        long start = 0;
        try {
            start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                Class<?> clz = Class.forName("reflect.Student");
                Student stu = (Student) clz.newInstance();
                Method method = clz.getMethod("setName", String.class);
                method.invoke(stu, "猿天地");
                System.out.println(stu.getName());
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
