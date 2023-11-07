package com.webhook.application.output;

import com.webhook.infrastructure.event.webhook.model.WebhookEvent;
import com.webhook.core.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebhookApplicationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publish(Partner partner, String requestBody, Throwable throwable) {
        WebhookEvent event = new WebhookEvent(this, partner, requestBody, throwable);
        log.info("Produce application webhook event: {}", event);
        eventPublisher.publishEvent(event);
    }

    public void publish(Partner partner, String requestBody, ResponseEntity<String> responseEntity) {
        WebhookEvent event = new WebhookEvent(this, partner, requestBody, responseEntity);
        log.info("Produce application webhook event: {}", event);
        eventPublisher.publishEvent(event);
    }
}
