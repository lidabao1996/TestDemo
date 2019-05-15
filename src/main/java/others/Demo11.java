package others;

public class Demo11 {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
