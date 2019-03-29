package thread;

public class DeadLock {
    final Object lockA = new Object();
    final Object lockB = new Object();

    public static void main(String[] args) {
        DeadLock demo = new DeadLock();
        demo.startLock();
    }

    public void startLock() {
        ThreadA a = new ThreadA(lockA, lockB);
        ThreadB b = new ThreadB(lockA, lockB);

        //开启线程
        a.start();
        b.start();
    }


    class ThreadA extends Thread {
        private Object lockA = null;
        private Object lockB = null;

        public ThreadA(Object a, Object b) {
            this.lockA = a;
            this.lockB = b;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println("*** Thread A: ***: Lock A");
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("*** Thread A: ***: Lock B");
                }
            }
            System.out.println("*** Thread A: ***: Finished");
        }
    }

    class ThreadB extends Thread {
        private Object lockA = null;
        private Object lockB = null;

        public ThreadB(Object a, Object b) {
            this.lockA = a;
            this.lockB = b;
        }

        @Override
        public void run() {
            synchronized (lockB) {
                System.out.println("***Thread B:***:Lock B");
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("*** Thread B: ***: Lock A");
                }

            }
            System.out.println("*** Thread B: ***: Finished");
        }

    }
}
