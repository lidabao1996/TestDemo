package suanfa;

public class Demo2 {
    public static void main(String[] args) {
        for (int x = 1; x < 100000; x++) {
            if (Math.sqrt(x + 100) % 1 == 0) {
                System.out.println("100平方数：" + x);
            }

            if (Math.sqrt(x + 100 + 168) % 1 == 0) {
                System.out.println("+100+168平方数:" + x);
            }
        }
    }
}
