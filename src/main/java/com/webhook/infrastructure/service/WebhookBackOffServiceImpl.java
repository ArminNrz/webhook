package com.webhook.infrastructure.service;

import com.webhook.core.partner.PartnerDomainService;
import com.webhook.core.webhook.WebhookBackOffService;
import com.webhook.core.webhook.cache.CacheWebhook;
import com.webhook.infrastructure.service.helper.WebhookBackOffHelper;
import com.webhook.infrastructure.service.requestsender.RequestSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebhookBackOffServiceImpl implements WebhookBackOffService {

    private final RequestSender requestSender;
    private final WebhookBackOffHelper backOffHelper;
    private final PartnerDomainService partnerDomainService;

    @Override
    public void sendBackOffAttempt(CacheWebhook cacheWebhook) {
        var partner = partnerDomainService.findById(cacheWebhook.partnerId());
        var transferJsonObject = cacheWebhook.body();

        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture
                .supplyAsync(() -> requestSender.send(partner, transferJsonObject), executor)
                .whenComplete((responseEntity, throwable) -> backOffHelper.callBack(responseEntity, throwable, cacheWebhook, partner));
    }
}
