package com.wk.wkshop.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class RedisTest {

    //@Test
    public void testRedis1() {
        Jedis jedis = new Jedis("10.31.161.13", 6379);
        System.out.println(jedis.get("foo"));
        jedis.set("laowu", "老吴");
        System.out.println(jedis.get("laowu"));
        jedis.close();
    }

    //@Test
    public void testRedis2() {
        //获取jedis池
        JedisPool pool = new JedisPool("10.31.161.13", 6379);
        //获取Jedis对象
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("foo"));
        System.out.println(jedis.get("laowu"));
        System.out.println(jedis.hget("mykey", "one"));
        //关闭连接
        jedis.close();
        pool.close();
        System.out.println(pool.isClosed());
        System.out.println(pool.isClosed());
    }

    //@Test
    public void testRedis3() {
        //创建集群节点集合
        Set<HostAndPort> nodes = new HashSet<>();
        //将6个节点加入到集合中
        nodes.add(new HostAndPort("10.31.161.13", 9001));
        nodes.add(new HostAndPort("10.31.161.13", 9002));
        nodes.add(new HostAndPort("10.31.161.13", 9003));
        nodes.add(new HostAndPort("10.31.161.13", 9004));
        nodes.add(new HostAndPort("10.31.161.13", 9005));
        nodes.add(new HostAndPort("10.31.161.13", 9006));
        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //存入数据
        jedisCluster.set("name", "万康");
        System.out.println(jedisCluster.get("name"));
        jedisCluster.hset("k", "f", "gakki");
        System.out.println(jedisCluster.hget("k", "f"));
        //关闭连接
        jedisCluster.close();
    }
}
