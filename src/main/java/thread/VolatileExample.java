package thread;

public class VolatileExample {

    // 以下代码来源于【参考 1】
    static int num = 0;
    volatile static boolean v = false;

    public static void writer() {

        num = 42;
        v = true;
    }

    public static void reader() {
        if (v == true) {
            System.out.println("num = " + num);
        }
    }


    public static void main(String[] args) {
        writer();
        reader();
    }

}
