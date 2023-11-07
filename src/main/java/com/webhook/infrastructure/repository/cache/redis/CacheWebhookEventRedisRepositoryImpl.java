package com.webhook.infrastructure.repository.cache.redis.impl;

import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.infrastructure.repository.cache.redis.CacheWebhookEventRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CacheWebhookEventRedisRepositoryImpl implements CacheWebhookEventRedisRepository {
    @Override
    public void save(CacheWebhook cacheWebhook) {

    }
}
