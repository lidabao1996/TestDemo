package others;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo23 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,11,10L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1),new MyThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("hello 张大磊");
                return "Success";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    static class MyThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);


        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = "MyThread"+count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }
}
