package com.webhook.infrastructure.event.webhook;

import com.webhook.application.input.partner.PartnerApplicationLogService;
import com.webhook.infrastructure.event.webhook.model.WebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebhookEventConsumer {

    private final PartnerApplicationLogService applicationLogService;

    @EventListener
    public void consumeApplicationEvent(WebhookEvent event) {
        log.info("Consume webhook application event: {}", event);
        applicationLogService.persist(event);
    }
}
