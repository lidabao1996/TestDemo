package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class NioServer {
    static int clintCount = 0;
    static AtomicInteger counter = new AtomicInteger(0);
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);

            channel.register(selector, SelectionKey.OP_ACCEPT);
            channel.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();


                    if (key.isAcceptable()) {
                        ServerSocketChannel channel1 = (ServerSocketChannel) key.channel();
                        SocketChannel sc = null;

                        while ((sc = channel1.accept()) != null) {
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            InetSocketAddress rsa = (InetSocketAddress) sc.socket().getRemoteSocketAddress();

                            System.out.println("--->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId());


                        }

                    } else if (key.isReadable()) {
                        //先将“读”从感兴趣操作移出，待把数据从通道中读完后，再把“读”添加到感兴趣操作中
                        //否则，该通道会一直被选出来
                        key.interestOps(key.interestOps() & (SelectionKey.OP_READ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static void processWithNewThread(SocketChannel sc, SelectionKey key) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementAndGet();
                try {
                    String result = readBytes(sc);
                    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    System.out.println(time() + "->" + result + "->" + Thread.currentThread().getId() + ":" + counter.get());
                    sc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                counter.decrementAndGet();
            }
        }).start();

    }


    static String readBytes(SocketChannel sc) throws Exception {
        long start = 0;
        int total = 0;
        int count = 0;

        ByteBuffer bb = ByteBuffer.allocate(1024);
        long begin = System.currentTimeMillis();

        while ((count = sc.read(bb)) > -1) {
            if (start < 1) {
                start = System.currentTimeMillis();
            }

            total += count;
            bb.clear();
        }

        long end = System.currentTimeMillis();
        return "wait=" + (start - begin) + "ms,read=" + (end - start) + "ms,total=" + total + "bs";
    }


    static String time() {
        return sdf.format(new Date());
    }
}
