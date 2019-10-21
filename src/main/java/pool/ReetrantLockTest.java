package pool;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public interface ReetrantLockTest {
    //支持中断
    void lockInterruptibly()throws InterruptedException;
    //支持超时的API
    boolean tryLock(long time, TimeUnit unit)throws InterruptedException;

    //支持非阻塞获取锁的API
    boolean tryLock();

}
