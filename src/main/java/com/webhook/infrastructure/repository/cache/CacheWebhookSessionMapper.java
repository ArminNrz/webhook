package com.webhook.infrastructure.repository.cache;

import com.webhook.core.webhook.cache.CacheWebhook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheWebhookSessionMapper {

    CacheWebhookSession toSession(CacheWebhook cacheWebhook) {
        return new CacheWebhookSession(
                cacheWebhook.cacheId().id(),
                cacheWebhook.partnerId().id(),
                cacheWebhook.partnerUrl().url(),
                cacheWebhook.backoffPolicy().tryCount(),
                cacheWebhook.body()
        );
    }

    public CacheWebhook toDomain(CacheWebhookSession session) {
        return CacheWebhook.of(
                session.cacheId(),
                session.partnerId(),
                session.partnerUrl(),
                session.tryCount(),
                session.body()
        );
    }
}
