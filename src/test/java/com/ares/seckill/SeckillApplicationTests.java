package com.ares.seckill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SeckillApplicationTests {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedisConnection(){
        redisTemplate.opsForValue().set("aa","bbb");
        System.out.println(redisTemplate.opsForValue().get("aa"));
    }
}
