package com.webhook.infrastructure.repository;

import com.webhook.core.vo.CacheId;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.core.webhook.cache.CacheWebhookRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CacheWebhookRepositoryAdapter implements CacheWebhookRepository {
    @Override
    public void save(CacheWebhook cacheWebhook) {

    }

    @Override
    public void update(CacheWebhook cacheWebhook) {

    }

    @Override
    public Optional<CacheWebhook> findById(CacheId id) {
        return Optional.empty();
    }

    @Override
    public void remove(CacheId id) {

    }
}
