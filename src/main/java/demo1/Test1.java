package demo1;

public class Test1 {
    public static void main(String[] args) {
        int j = 0;
        j = sum();
        System.out.println(++j);

    }

    public static int sum() {
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                return i;
            }
        }
        return 1;
    }
}
