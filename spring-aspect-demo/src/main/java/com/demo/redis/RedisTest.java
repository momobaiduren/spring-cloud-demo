package com.demo.redis;

import java.util.concurrent.TimeUnit;
import redis.clients.jedis.Jedis;

/**
 * @author zhanglong
 * @title: RedisTest
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-2618:56
 */

public class RedisTest {

    public static void main( String[] args ) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("zhanglong", "zhanglong");
        System.out.println(jedis.get("zhanglong"));
    }

    public synchronized void tryLock( String key, String value, Long expi, TimeUnit seconds ) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//    jedis.sc
    }


}

