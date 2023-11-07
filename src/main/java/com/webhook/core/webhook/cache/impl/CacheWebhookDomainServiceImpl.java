package com.webhook.core.webhook.cache;

import com.webhook.core.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheWebhookDomainServiceImpl {

    private final CacheWebhookRepository repository;

    public void persist(Partner partner, int tryCount) {
        CacheWebhook cacheWebhook = CacheWebhook.init(partner, tryCount);
        log.debug("Try to save cache webhook: {}", cacheWebhook);
        repository.save(cacheWebhook);
    }

    public
}
