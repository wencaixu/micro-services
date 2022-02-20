package com.ares.seckill.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //@Autowired
    //private RedisScript<Boolean> redisScript;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("123","12142");
        Object o = redisTemplate.opsForValue().get("123");
        System.out.println(o);
    }

    /*@Test
    void defaultRedisScript() {
        String uuid = UUID.randomUUID().toString();

        ValueOperations<String, Object> value = redisTemplate.opsForValue();

        Boolean isLock = value.setIfAbsent(uuid, true, 10, TimeUnit.SECONDS);

        if(isLock){
            value.set("name","xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            System.out.println(valueOperations.get("k1"));
            Boolean result = (Boolean) redisTemplate.execute(script, Collections.singletonList("k1"), value);
            System.out.println(result);
        }else {
            System.out.println("有线程请使用，请稍后");
        }
    }*/

    /*@Test
    public void testLock03(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = UUID.randomUUID().toString();
        Boolean isLock = valueOperations.setIfAbsent("k1", value, 120, TimeUnit.SECONDS);
        if(isLock){
            valueOperations.set("name","xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            System.out.println(valueOperations.get("k1"));
            Boolean result = (Boolean) redisTemplate.execute(redisScript, Collections.singletonList("k1"), value);
            System.out.println(result);
        }else {
            System.out.println("有线程请使用，请稍后");
        }
    }*/
}
