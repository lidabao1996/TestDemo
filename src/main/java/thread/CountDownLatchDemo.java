package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程同步
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {




        try {
            while (true) {
                //计数器
                CountDownLatch latch = new CountDownLatch(2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //业务代码

                        //执行完业务代码执行latch
                        latch.countDown();
                    }
                });


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //业务代码

                        //执行完业务代码执行latch
                        latch.countDown();
                    }
                });

                latch.await();


                //执行持久化操作
                //save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
