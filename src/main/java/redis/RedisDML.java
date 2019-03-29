package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author lifang
 * @version 1.0
 * redis JavaAPI操作
 * @create date 2019-02-19
 */
public class RedisDML {
    public static void main(String[] args) {

        testTransaction();
    }

    public static void testTransaction() {
        Jedis jedis = new Jedis("192.168.80.116");
        Transaction transaction = null;
        try {
            transaction = jedis.multi();
            transaction.decrBy("xiaokeai", 100);
            transaction.incrBy("nn", 100);
            transaction.exec();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.discard();
        }
        jedis.disconnect();
    }

}
