package com.webhook.infrastructure.repository.cache;

import java.io.Serializable;

public record CacheWebhookSession(
        String cacheId,
        String partnerId,
        String partnerUrl,
        int tryCount,
        String body
) implements Serializable {
}
