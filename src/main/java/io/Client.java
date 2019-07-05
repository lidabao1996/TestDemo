package io;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static void main(String[] args) {

        try {
            for (int i = 0; i < 20; i++) {
                Socket s = new Socket();
                s.connect(new InetSocketAddress("localhost", 8080));
                processWithNewThread(s, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static void processWithNewThread(Socket s, int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((new Random().nextInt(6) + 5) * 1000);

                    s.getOutputStream().write(prepareBytes());

                    Thread.sleep(1000);
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    static byte[] prepareBytes() {
        byte[] bytes = new byte[1024 * 1024 * 1];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 1;
        }

        return bytes;
    }

}
