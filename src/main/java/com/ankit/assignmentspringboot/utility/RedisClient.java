package com.ankit.assignmentspringboot.utility;

import redis.clients.jedis.Jedis;

public class RedisClient {
    private static Jedis client;

    public static Jedis getInstance() {
            try {
                if(client == null){
                        client = new Jedis("redis://localhost:6379");
                        System.out.println("connected to redis...");
                }
                return client;
            } catch (Exception e) {
                System.out.println("failed to connect to redis");
                System.out.println(e);
                return null;
            }
    }
}
