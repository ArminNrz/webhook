package com.webhook.infrastructure.repository.cache.redis;

public class RedisConstant {
    private static final String REDIS_PREFIX = "webhook:";

    public static final String REDIS_WEBHOOK_CACHE_PREFIX = REDIS_PREFIX + "cache:";
    public static final String REDIS_WEBHOOK_EVENT_PREFIX = REDIS_PREFIX + "event:";
}
