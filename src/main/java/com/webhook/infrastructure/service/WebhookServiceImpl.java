package com.webhook.infrastructure.service;

import com.webhook.core.partner.Partner;
import com.webhook.core.webhook.WebhookService;
import com.webhook.core.webhook.common.BaseWebhookDomain;
import com.webhook.infrastructure.service.helper.WebhookBackOffHelper;
import com.webhook.infrastructure.service.helper.WebhookFirstCallBackHelper;
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
public class WebhookServiceImpl<I extends BaseWebhookDomain> implements WebhookService<I> {

    private final RequestSender requestSender;
    private final WebhookFirstCallBackHelper callBackHelper;
    private final WebhookBackOffHelper backOffHelper;

    @Override
    public void sendFirstAttempt(Partner partner, I transferObject) {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture
                .supplyAsync(() -> requestSender.send(partner, transferObject.toJson()), executor)
                .whenComplete((responseEntity, throwable) -> callBackHelper.callBack(responseEntity, throwable, transferObject.toJson(), partner));
    }
}
