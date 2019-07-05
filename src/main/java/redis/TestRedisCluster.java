package redis;

import java.util.HashSet;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestRedisCluster {

	public static void main(String[] args) throws Exception {
		// 指定集群的节点
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.56.72", 6379));
		nodes.add(new HostAndPort("192.168.56.72", 6380));
		nodes.add(new HostAndPort("192.168.56.72", 6381));
		nodes.add(new HostAndPort("192.168.56.72", 6382));
		nodes.add(new HostAndPort("192.168.56.72", 6383));
		nodes.add(new HostAndPort("192.168.56.72", 6384));

		// 创建集群
		JedisCluster cluster = new JedisCluster(nodes);

		// 插入3*16383条数据
		for (int i = 0; i < 3 * 16383; i++) {
			System.out.println("插入数据：" + i);
			cluster.set("mykey" + i, "myvalue:" + i);
		}

		// 关闭集群客户端
		cluster.close();
		System.out.println("完成");
	}
}
