package com.webhook.infrastructure.repository.cache.redis;

import com.webhook.core.vo.CacheId;
import com.webhook.infrastructure.repository.cache.CacheWebhookSession;

import java.util.Optional;

public sealed interface CacheWebhookRedisRepository permits CacheWebhookRedisRepositoryImpl {
    void save(CacheWebhookSession session);
    Optional<CacheWebhookSession> findById(CacheId id);
    void remove(CacheId id);
}
