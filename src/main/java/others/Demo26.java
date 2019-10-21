package others;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo26 {
    private static AtomicInteger orderNum = new AtomicInteger(0);

    @Test
    public void test() {
        int i = 0;
        for (; i < 10; i++) {

            new Numb(i).start();
        }


    }

    class Numb extends Thread {
        int num;
        final Object lock = new Object();

        public Numb(int n) {
            num = n;
        }

        @Override
        public void run() {
            synchronized (orderNum) {
                while (num != orderNum.intValue()) {
                    try {
                        orderNum.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(num);
                orderNum.getAndIncrement();
                orderNum.notifyAll();
            }
        }

    }
}
