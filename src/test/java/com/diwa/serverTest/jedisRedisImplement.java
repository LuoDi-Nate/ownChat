package com.diwa.serverTest;

import redis.clients.jedis.Jedis;

/**
 * Created by di on 7/5/15.
 */
public class jedisRedisImplement {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        System.out.println(jedis.get("foodkhjfkjdj"));
        System.out.println(jedis.get("jedis"));
    }
}
