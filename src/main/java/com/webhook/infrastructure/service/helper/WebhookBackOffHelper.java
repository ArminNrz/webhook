package com.webhook.infrastructure.service;

import com.webhook.core.partner.Partner;
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

    public void callBack(ResponseEntity<String> responseEntity, Throwable throwable, String transferJsonObject, Partner partner) {

    }
}
