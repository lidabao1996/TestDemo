package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池
 * 采用生产者-消费者模式
 *
 * 线程池的使用者是生产者
 *
 * 线程池本身是消费者
 *
 *
 *
 */
public class MyTreadPool {

    //利用阻塞队里实现消费者 - 生产者模式
    BlockingQueue<Runnable> workQueue;

    //保存内部工作线程
    List<WorkerThread> threads = new ArrayList<>();

    /**
     * 线程池构造方法
     * @param poolSize
     * @param workQueue
     */
    public MyTreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int idx = 0; idx < poolSize; idx++) {
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);
        }
    }

    void exetute(Runnable command) {
        try {
            workQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        //一个有界队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(2);
        MyTreadPool pool = new MyTreadPool(10, workQueue);
        pool.exetute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
    }
}

