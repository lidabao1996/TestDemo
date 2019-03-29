package thread;

public class ThreadMain {
    public static void main(String[] args){

        Thread2 th2 = new Thread2();
        Thread thread = new Thread(th2);
        thread.setPriority(10);
        //jdk里面写，导致此线程开始执行，java虚拟机调用此线程的run方法

        //只要start，就会调用此线程的run方法，此刻象征着线程的开启。

        //如果直接调用run方法，虽然都是让run方法跑起来，但是它不是进程了，相当于普通方法的一个类。
        thread.start();





        //2019-03-05
        ThreadTest th = new ThreadTest();
        Thread thTest = new Thread(th);
        System.out.println(thTest.getPriority());
        thTest.start();
    }
}
