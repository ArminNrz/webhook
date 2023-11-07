package com.webhook.infrastructure.event.webhook;

import com.webhook.application.input.webhook.WebhookCacheApplicationService;
import com.webhook.core.vo.CacheId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebhookRedisEventConsumer implements MessageListener {

    private final WebhookCacheApplicationService applicationService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        String channel = new String(message.getChannel());
        log.debug("Received Redis event from channel: {}", channel);
        log.info("Expired redis with key: {}", key);

        String[] keyItems = key.split(":");
        String cacheId = keyItems[2];

        applicationService.reCall(new CacheId(cacheId));
    }
}
