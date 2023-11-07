package com.webhook.infrastructure.service;

import com.webhook.application.output.WebhookApplicationEventPublisher;
import com.webhook.core.partner.Partner;
import com.webhook.core.webhook.cache.CacheWebhookDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebhookFirstCallBackHelper {

    private final CacheWebhookDomainService domainService;
    private final WebhookApplicationEventPublisher applicationEventPublisher;

    public void callBack(ResponseEntity<String> responseEntity, Throwable throwable, String requestBody, Partner partner) {
        if (throwable != null) {
            log.error("There is an error occurred in calling webhook partner: {}", partner, throwable);
            domainService.persist(partner, 1);
            applicationEventPublisher.publish(partner, requestBody, throwable);
            return;
        }

        log.info("Called webhook with url: {}, response: {}", partner.url(), responseEntity);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.warn("Webhook called to partner url: {}, is not success with status code: {}", partner.url(), responseEntity.getStatusCode().value());
            domainService.persist(partner, 1);
        }
        applicationEventPublisher.publish(partner, requestBody, responseEntity);
    }
}
