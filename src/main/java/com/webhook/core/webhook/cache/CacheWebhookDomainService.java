package com.webhook.core.webhook.cache;

import com.webhook.core.partner.Partner;
import com.webhook.core.vo.CacheId;

public interface CacheWebhookDomainService {
    void persist(Partner partner, int tryCount, String body);
    CacheWebhook findById(CacheId cacheId);
    void update(CacheWebhook cacheWebhook);
}
