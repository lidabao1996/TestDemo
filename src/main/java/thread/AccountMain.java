package thread;

import java.util.Random;

public class AccountMain {
    public static void main(String[] args) {
        WechatAccount a = new WechatAccount("lifan");
        a.setBalance(100);
        WechatAccount b = new WechatAccount("xiaokeai");
        b.setBalance(100);
        WechatAccount c = new WechatAccount("stupid");
        c.setBalance(100);

        WechatAccount target = new WechatAccount("sophia");
//        a.transfer(target,100);


        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("x = " + x);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int x = new Random().nextInt(4);

                    if (x == 0) {
                        a.transfer(target, 10);
                    } else if (x == 1) {
                        b.transfer(target, 10);
                    } else if (x == 2) {
                        c.transfer(target, 10);
                    } else {
                        a.transfer(b, 5);
                    }
                }
            }).start();


        }

    }
}
