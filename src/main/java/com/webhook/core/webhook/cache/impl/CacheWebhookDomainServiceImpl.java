package com.webhook.core.webhook.cache.impl;

import com.webhook.core.exception.CacheWebhookException;
import com.webhook.core.partner.Partner;
import com.webhook.core.vo.CacheId;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.core.webhook.cache.CacheWebhookDomainService;
import com.webhook.core.webhook.cache.CacheWebhookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheWebhookDomainServiceImpl implements CacheWebhookDomainService {

    private final CacheWebhookRepository repository;

    @Override
    public void persist(Partner partner, int tryCount, String body) {
        CacheWebhook cacheWebhook = CacheWebhook.init(partner, tryCount, body);
        log.debug("Try to save cache webhook: {}", cacheWebhook);
        repository.save(cacheWebhook);
    }

    @Override
    public CacheWebhook findById(CacheId cacheId) {
        return repository.findById(cacheId)
                .orElseThrow(() -> new CacheWebhookException("No cache exist with this id: " + cacheId.id()));
    }

    @Override
    public void update(CacheWebhook cacheWebhook) {
        log.debug("Try to update cache webhook: {}", cacheWebhook);
        repository.save(cacheWebhook);
    }


}
