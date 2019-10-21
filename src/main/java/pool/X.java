package pool;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java SDK 的锁实现起来非常复杂，它利用了volatile相关的Happens-Before规则
 * Java SDK里面的ReentrantLock,内部持有一个volatile的成员变量state
 */
public class X {
    private final Lock rt = new ReentrantLock();
    int value;

    public void addOne() {
        rt.lock();
        try {
            value += 1;
        } finally {
            rt.unlock();
        }
    }

    @Test
    public void test(){

    }

}
