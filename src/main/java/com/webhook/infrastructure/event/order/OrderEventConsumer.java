package com.webhook.infrastructure.event.order;

import com.webhook.application.input.webhook.WebhookOrderApplicationService;
import com.webhook.infrastructure.event.order.model.OrderEventModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final WebhookOrderApplicationService applicationService;

    @RabbitListener(queues = {"webhook.queue"})
    public void consume(OrderEventModel eventModel) {
        log.info("Consume order event model: {}", eventModel);
        applicationService.processOrderEvent(eventModel);
    }
}
