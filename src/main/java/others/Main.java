package others;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService e = Executors.newCachedThreadPool();
        List<FutureTask> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Integer> c = new CountCallable(i);
            FutureTask<Integer> task = new FutureTask<>(c);
            callables.add(task);
            e.submit(task);
        }
        e.shutdown();
        callables.forEach(futureTask -> {
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException | ExecutionException e1) {
                e1.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start) / 1000);

    }


    static class CountCallable implements Callable<Integer> {
        private int num;

        public CountCallable(int num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep((9 - num) * 1000);
            return num;
        }
    }
}
