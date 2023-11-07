package com.webhook.infrastructure.repository.cache.redis;

import com.webhook.core.webhook.cache.CacheWebhook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public non-sealed class CacheWebhookEventRedisRepositoryImpl implements CacheWebhookEventRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(CacheWebhook cacheWebhook) {
        String id = cacheWebhook.cacheId().id();
        int backoffTime = cacheWebhook.backoffPolicy().backoffTimeMinutes();
        String idKey = RedisConstant.REDIS_WEBHOOK_EVENT_PREFIX + id;

        redisTemplate.opsForValue().set(idKey, id, backoffTime, TimeUnit.MINUTES);
    }
}
