package reflect;

/**
 * 测试直接创建对象消耗时间
 */
public class Demo2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Student stu = new Student();
            stu.setName("猿天地");
            System.out.println(stu.getName());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
