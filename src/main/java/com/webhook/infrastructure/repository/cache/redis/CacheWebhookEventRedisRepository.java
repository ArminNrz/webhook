package com.webhook.infrastructure.repository.cache.redis;

import com.webhook.core.webhook.cache.CacheWebhook;

public sealed interface CacheWebhookEventRedisRepository permits CacheWebhookEventRedisRepositoryImpl {
    void save(CacheWebhook cacheWebhook);
}
