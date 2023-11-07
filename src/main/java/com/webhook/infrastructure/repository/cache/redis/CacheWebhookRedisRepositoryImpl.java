package com.webhook.infrastructure.repository.cache.redis.impl;

import com.webhook.core.vo.CacheId;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.infrastructure.repository.cache.redis.CacheWebhookRedisRepository;
import com.webhook.infrastructure.repository.cache.redis.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class CacheWebhookRedisRepositoryImpl implements CacheWebhookRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.data.redis.TTL.cache}")
    private Integer REDIS_WEBHOOK_CACHE_TTL;

    @Override
    public void save(CacheWebhook cacheWebhook) {
        String id = cacheWebhook.cacheId().id();
        String idKey = RedisConstant.REDIS_WEBHOOK_CACHE_PREFIX + id;

        redisTemplate.opsForValue().set(idKey, cacheWebhook, REDIS_WEBHOOK_CACHE_TTL, TimeUnit.SECONDS);
    }

    @Override
    public Optional<CacheWebhook> findById(CacheId cacheId) {
        String id = cacheId.id();
        String idKey = RedisConstant.REDIS_WEBHOOK_CACHE_PREFIX + id;
        CacheWebhook findObject = (CacheWebhook) redisTemplate.opsForValue().get(idKey);

        Optional<CacheWebhook> result;
        if (ObjectUtils.isEmpty(findObject)) {
            result = Optional.empty();
        }
        else {
            result = Optional.of(findObject);
        }

        return result;
    }

    @Override
    public void remove(CacheId cacheId) {
        String id = cacheId.id();
        String idKey = RedisConstant.REDIS_WEBHOOK_CACHE_PREFIX + id;
        redisTemplate.delete(idKey);
    }
}
