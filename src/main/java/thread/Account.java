package thread;

/**
 * 2019-04-19
 * 受保护资源和锁之间是N:1的关系
 *
 * 用不同的锁对受保护资源进行精细化管理，能够提升性能，叫细粒度锁。
 *
 *
 */
public class Account {
    private final Object balLock = new Object();
    private Integer balance;
    private final Object pwLock = new Object();
    private String password;

    public void withDraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }


}
