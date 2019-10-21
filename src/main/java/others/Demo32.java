package others;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo32 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 111111;
    }

    public static void main(String[] args) {
        Callable<Integer> callable = new Demo32();
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread th1 = new Thread(task);


        th1.start();


        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
