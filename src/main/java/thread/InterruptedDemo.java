package thread;

/**
 * @author sophia
 * @since 2019-04-30
 * InterruptedException异常处理要谨慎小心
 */
public class InterruptedDemo {
    public static void main(String[] args) {

        Thread th = Thread.currentThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                th.interrupt();
            }
        }).start();

        while (true) {
            if (th.isInterrupted()) {
                break;
            }
            //这里是逻辑代码
            System.out.println("嘿");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //重新设置中断标，因为抛出InterruptedException异常后中断标会被自动清理。
                System.out.println("哈");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}






