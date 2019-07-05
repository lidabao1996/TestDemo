package io;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class BioServer {
    static AtomicInteger counter = new AtomicInteger(0);
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {

        try {
            //服务器上有个在某个端口监听,接收到客户端连接后，会创建一个Socket,并把它交给一个线程后续处理。
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                //线程主要从Socket读取客户端传来的数据，然后进行业务处理，再把结果写入Socket传回客户端。

                //由于网络的原因，Socket创建后不一定能立刻从它上面读取数据，可能需要等一段时间，
                //此时线程也必须一致阻塞着。再向Socket写入数据是，也可能使线程阻塞。
                Socket s = ss.accept();
                proccessWithNewThread(s);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    static void proccessWithNewThread(Socket s) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                InetSocketAddress rsa = (InetSocketAddress) s.getRemoteSocketAddress();
                System.out.println(new Date().getTime() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + counter.incrementAndGet());
                try {
                    String result = readBytes(s.getInputStream());
                   // System.out.println(time() + "->" + result + "->"
                    // + Thread.currentThread().getId()
                    // + ":" + counter.getAndDecrement());
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }


    static String readBytes(InputStream inputStream) throws Exception {
        long start = 0;
        int total = 0;
        int count = 0;


        byte[] bytes = new byte[1024];
        //开始读数据的时间
        long begin = System.currentTimeMillis();

        while ((count = inputStream.read(bytes)) > -1) {
            if (start < 1) {
                //第一次读到数据的时间
                start = System.currentTimeMillis();
            }
            total += count;
        }


        long end = System.currentTimeMillis();


        return "wait:" + (start - begin) + "ms,read" + (end - start) + "ms,total" + total + "bs";
    }


}
