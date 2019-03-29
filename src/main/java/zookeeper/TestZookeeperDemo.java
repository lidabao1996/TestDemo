package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestZookeeperDemo {

    //定义一个共享资源
    private static int NUMBER = 10;

    private static void getNumber() {
        //业务方法
        System.out.println("*******业务方法开始*******");
        System.out.println("当前值：" + NUMBER);
        NUMBER--;

        //睡2秒，该客户端依然拿着这把锁
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*******业务方法结束*******\n\n");
    }


    public static void main(String[] args) {
        //定义一个retry policy（重试策略）
        /*
         * 1000: 每次等待的时间
         * 10:重试的次数
         */
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);

        //创建一个ZK的客户端
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString("192.168.157.111:2181")  //ZK的地址
                .retryPolicy(policy)
                .build();
        //启动客户端
        cf.start();

        //在ZK中定义一把锁
        final InterProcessMutex lock = new InterProcessMutex(cf, "/mylock");

        //启动10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                public void run() {
                    try {
                        //请求得到锁，如果没有得到锁，使用retrypolicy重试
                        lock.acquire();

                        //访问共享资源
                        getNumber();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        //释放锁
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
