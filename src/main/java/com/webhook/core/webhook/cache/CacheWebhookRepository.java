package com.webhook.core.webhook.cache;

import com.webhook.core.vo.CacheId;

import java.util.Optional;

public interface CacheWebhookRepository {
    void save(CacheWebhook cacheWebhook);
    void update(CacheWebhook cacheWebhook);
    Optional<CacheWebhook> findById(CacheId id);
}
