package thread;

/**
 * @author sophia
 * @since 2019-04-30
 * InterruptedException异常处理要谨慎小心
 */
public class InterruptedDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("我在执行");


        });
        thread.start();
        Thread t2 = new Thread(() -> {
            System.out.println("我要中的th");
            thread.interrupt();
        });
        t2.start();
        //th.interrupt();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread th = Thread.currentThread();
        while (true) {
            if (th.isInterrupted()) {
                System.out.println("-----");
                break;
            }
            //这里是逻辑代码
            System.out.println("嘿");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //重新设置中断标，因为抛出InterruptedException异常后中断标会被自动清理。
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}






