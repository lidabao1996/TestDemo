package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {

    final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();

    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    //队列已满
    volatile boolean isFull = true;

    // 入队
    void enq(T x) {
        lock.lock();
        try {
            while (isFull) {
                // 等待队列不满
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 省略入队操作...
            // 入队后, 通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq() {
        lock.lock();
        try {
            while (isFull) {
                // 等待队列不空
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 省略出队操作...
            // 出队后，通知可入队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

