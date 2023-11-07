package com.webhook.infrastructure.repository.cache;

import com.webhook.core.vo.CacheId;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.core.webhook.cache.CacheWebhookRepository;
import com.webhook.infrastructure.repository.cache.redis.CacheWebhookEventRedisRepository;
import com.webhook.infrastructure.repository.cache.redis.CacheWebhookRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CacheWebhookRepositoryAdapter implements CacheWebhookRepository {

    private final CacheWebhookRedisRepository redisRepository;
    private final CacheWebhookEventRedisRepository eventRedisRepository;
    private final CacheWebhookSessionMapper sessionMapper;

    @Override
    public void save(CacheWebhook cacheWebhook) {
        var session = sessionMapper.toSession(cacheWebhook);
        redisRepository.save(session);
        log.info("Saved in redis cache webhook: {}",cacheWebhook);
        eventRedisRepository.save(cacheWebhook);
        log.debug("Saved event cache of webhook: {}", cacheWebhook);
    }

    @Override
    public void update(CacheWebhook cacheWebhook) {
        int backoffTime = cacheWebhook.backoffPolicy().backoffTimeMinutes();
        if (backoffTime == -1) {
            log.error("Detect more than standard webhook try for cache: {}, so remove it", cacheWebhook);
            redisRepository.remove(cacheWebhook.cacheId());
            return;
        }

        this.save(cacheWebhook);
    }

    @Override
    public Optional<CacheWebhook> findById(CacheId cacheId) {
        return redisRepository.findById(cacheId).map(sessionMapper::toDomain);
    }
}
