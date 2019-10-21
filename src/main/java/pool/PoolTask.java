package pool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PoolTask {
    BlockingQueue<Runnable> workQueue;
    List<WorkThread> threadFactory = new ArrayList<>();

    public PoolTask(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;

        for (int i = 0; i < poolSize; i++) {
            WorkThread work = new WorkThread();
            work.start();
            threadFactory.add(work);
        }

    }

    void execute(Runnable command) {
        workQueue.add(command);
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Runnable work = workQueue.take();
                    work.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //有界队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        PoolTask poolTask = new PoolTask(10, workQueue);
        poolTask.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(".....别激动，我要出现啦！");
                System.out.println("哈哈哈哈");
                //Thread.sleep(20000);
            }
        });

        poolTask.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(".....别激动，我要出现啦！");
                System.out.println("哈哈哈哈嗝~");
            }
        });
        poolTask.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(".....别激动，我要出现啦！");
                System.out.println("哈哈哈哈嗝~~~~");
            }
        });
    }
}
