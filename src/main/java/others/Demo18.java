package others;

public class Demo18 {
    public static void main(String[] args) {
        int i = 2;
        Long start = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleepOne();
            }
        }).start();
        sleepTwo();
        System.out.println(Thread.activeCount());
        System.out.println(System.currentTimeMillis() - start / 1000);
        while (Thread.activeCount() >= i) {
            Thread.yield();
        }
        System.out.println(System.currentTimeMillis() - start / 1000);
    }

    public static synchronized void sleepOne() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void sleepTwo() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
