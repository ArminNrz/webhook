package com.webhook.core.webhook;

import com.webhook.core.webhook.cache.CacheWebhook;

public interface WebhookBackOffService {
    void sendBackOffAttempt(CacheWebhook cacheWebhook);
}
