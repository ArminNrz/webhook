package com.webhook.infrastructure.event;

import com.webhook.application.input.webhook.WebhookOrderApplicationService;
import com.webhook.infrastructure.event.model.OrderEventModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final WebhookOrderApplicationService applicationService;

    //todo: consumer annotation
    public void consume(OrderEventModel eventModel) {
        log.info("Consume order event model: {}", eventModel);
        applicationService.processOrderEvent(eventModel);
    }
}
