package com.example.mydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by yanshao on 2018/11/23.
 */
@Configuration
public class RestConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate redisKeyValueSerializer() {
        //redis key和value值序列化，不序列话发现查到的key和value乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}