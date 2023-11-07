package com.webhook.infrastructure.service.helper;

import com.webhook.application.output.WebhookApplicationEventPublisher;
import com.webhook.core.partner.Partner;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.core.webhook.cache.CacheWebhookDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebhookBackOffHelper {

    private final CacheWebhookDomainService domainService;
    private final WebhookApplicationEventPublisher applicationEventPublisher;

    public void callBack(ResponseEntity<String> responseEntity, Throwable throwable, CacheWebhook cacheWebhook, Partner partner) {
        cacheWebhook.increaseTryCount();
        cacheWebhook.updateUrl(partner);
        if (throwable != null) {
            log.error("There is an error occurred in calling webhook partner: {}", cacheWebhook.partnerId(), throwable);
            domainService.update(cacheWebhook);
            applicationEventPublisher.publish(partner, responseEntity.getBody(), throwable);
        }

        log.info("Called webhook with url: {}, response: {}", partner.url(), responseEntity);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.warn("Webhook called to partner url: {}, is not success with status code: {}", partner.url(), responseEntity.getStatusCode().value());
            domainService.update(cacheWebhook);
        }
        applicationEventPublisher.publish(partner, cacheWebhook.body(), responseEntity);
    }
}
