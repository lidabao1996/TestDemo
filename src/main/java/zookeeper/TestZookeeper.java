package zookeeper;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestZookeeper {
    public volatile static int NUMBER = 10;

    private static void getNumber() {
        System.out.println("****抢票开始***");
        System.out.println("剩余票数" + NUMBER);
        NUMBER--;

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("****抢票结束***");
    }


    public static void main(String[] args) {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework cf = CuratorFrameworkFactory.builder().connectString("192.168.8.157.116:1281")
                .retryPolicy(policy)
                .build();
        cf.start();

        final InterProcessMutex lock = new InterProcessMutex(cf, "/myLock");

        

        for (int j = 0; j < 10; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.acquire();
                        //访问共享资源
                        getNumber();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }

}
