package com.example.dio.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        Cache<Object,Object> cache = Caffeine.newBuilder()
                .initialCapacity(3000)
                .maximumSize(5000)
                .expireAfterWrite(Duration.ofDays(15))
                .build();

        Cache<Object,Object> foodItemCache = Caffeine.newBuilder()
                .initialCapacity(5000)
                .maximumSize(20000)
                .expireAfterWrite(Duration.ofMinutes(60))
                .build();

        caffeineCacheManager.registerCustomCache("blacklisted-cache",cache);
        caffeineCacheManager.registerCustomCache("food-item-cache",foodItemCache);
        return caffeineCacheManager;
    }
}
