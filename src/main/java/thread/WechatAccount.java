package thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 20190429
 * java并发包中的ReentrantLock
 */
public class WechatAccount {
    private final Lock lock = new ReentrantLock();
    private int balance;
    public String name;

    public WechatAccount(String name) {
        this.name = name;
    }

    public void transfer(WechatAccount tar, int amt) {
        boolean success = false;
        Thread th = Thread.currentThread();

        while (!th.isInterrupted() && !success) {
            if (this.lock.tryLock()) {
                try {
                    try {
                        if (tar.lock.tryLock(new Date().getTime(), TimeUnit.NANOSECONDS)) {//②
                            try {
                                this.balance -= amt;
                                tar.balance += amt;
                                success = true;
                                System.out.println(name + "还剩：" + this.balance);
                                System.out.println(tar.name + "有了：" + tar.balance);
                            } finally {
                                tar.lock.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    this.lock.unlock();

                }
            }
        }

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
