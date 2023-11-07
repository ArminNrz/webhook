package com.webhook.application.input.webhook;

import com.webhook.core.vo.CacheId;
import com.webhook.core.webhook.WebhookBackOffService;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.core.webhook.cache.CacheWebhookDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebhookCacheApplicationService {

    private final CacheWebhookDomainService domainService;
    private final WebhookBackOffService webhookBackOffService;

    @Transactional
    public void reCall(CacheId cacheId) {
        CacheWebhook cacheWebhook = domainService.findById(cacheId);
        webhookBackOffService.sendBackOffAttempt(cacheWebhook);
    }
}
