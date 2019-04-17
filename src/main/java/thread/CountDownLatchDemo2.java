package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程同步,优化版
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) {
        //线程池
        Executor executor = Executors.newFixedThreadPool(2);
        try {
            while (true) {
                //计数器
                CountDownLatch latch = new CountDownLatch(2);

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //查询orders执行业务代码
                        //queryOrders();
                        latch.countDown();
                    }
                });


                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //执行业务代码
                        //queryTrackOrders();
                        latch.countDown();
                    }
                });

                //实现计数器等于0的状态
                latch.await();


                //执行持久化操作
                //save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
