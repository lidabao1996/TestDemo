package thread;

public class LockDemo {
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private int value = 0;

    public static void main(String[] args) {


    }

    private void addOne() {
        synchronized (lock1) {
            synchronized (lock2) {
                value += 1;
            }
        }
    }

    private int get() {

        synchronized (lock1) {
            synchronized (lock2) {
                return value;
            }
        }
    }
}
