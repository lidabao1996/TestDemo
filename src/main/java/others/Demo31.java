package others;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo31 {

    private LinkedBlockingQueue<Runnable> workerQueue;

    List<WorkThread> workThreads = new ArrayList<>();

    public Demo31(int poolSize, LinkedBlockingQueue<Runnable> workerQueue) {
        this.workerQueue = workerQueue;
        for (int i = 0; i < poolSize; i++) {
            WorkThread worker = new WorkThread();
            worker.start();
            workThreads.add(worker);
        }


    }

    public void execute(Runnable cammond) {
        try {
            System.out.println("put");
            workerQueue.put(cammond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable take = workerQueue.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Demo31 pool = new Demo31(1, new LinkedBlockingQueue<>(2));
        for (int i = 0; i < 20; i++) {
            final int c = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("张大磊喝了" + (c + 1) + "杯全塘陨石拿铁");
                }
            });
        }

    }
}
