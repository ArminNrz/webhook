package com.webhook.infrastructure.repository.cache.redis;

import com.webhook.core.vo.CacheId;
import com.webhook.infrastructure.repository.cache.CacheWebhookSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public non-sealed class CacheWebhookRedisRepositoryImpl implements CacheWebhookRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.data.redis.TTL.cache}")
    private Integer REDIS_WEBHOOK_CACHE_TTL;

    @Override
    public void save(CacheWebhookSession session) {
        String id = session.cacheId();
        String idKey = RedisConstant.REDIS_WEBHOOK_CACHE_PREFIX + id;

        redisTemplate.opsForValue().set(idKey, session, REDIS_WEBHOOK_CACHE_TTL, TimeUnit.MINUTES);
    }

    @Override
    public Optional<CacheWebhookSession> findById(CacheId cacheId) {
        String id = cacheId.id();
        String idKey = RedisConstant.REDIS_WEBHOOK_CACHE_PREFIX + id;
        CacheWebhookSession findObject = (CacheWebhookSession) redisTemplate.opsForValue().get(idKey);

        Optional<CacheWebhookSession> result;
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
