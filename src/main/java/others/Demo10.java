package others;

public class Demo10 {
    public static void main(String[] args) {
        int num = 707829217;

        for (int i = 1; i <= num; i++) {
            if (num % (i + 2) == 0) {
                System.out.println("num = " + (i+2));
            }
        }


    }
}
