package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static AtomicInteger race = new AtomicInteger(0);

    public static synchronized void increase() {
        //这个方法最后调用compareAndSwapInt,它需要操作三个值，内存地址V值，旧的预期值A，即将要更新的目标值B
        race.getAndDecrement();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }


        while (Thread.activeCount() > 1) {
            Thread.yield();
            System.out.println("race = " + race);
         }

    }
}
