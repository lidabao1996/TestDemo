package suanfa;

public class Demo1 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 4; i++) {

            for (int j = 1; j <= 4; j++) {

                for (int k = 1; k <= 4; k++) {
                    count += 1;
                    System.out.println(i*100+j*10+k);
                }
            }
        }

        System.out.println("共"+count+"个三位数");
    }
}
