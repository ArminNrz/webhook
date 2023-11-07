package com.webhook.infrastructure.event.order;

import com.webhook.core.util.ULIDGenerator;
import com.webhook.infrastructure.event.order.model.OrderEventModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class OrderEventConsumerTest {

    @Autowired
    OrderEventConsumer consumer;

    @Test
    void consume() {
        var eventModel = new OrderEventModel(UUID.randomUUID().toString(), "01hefzwrgha6p8etmmbmbg44wr", "123456");
        consumer.consume(eventModel);
    }
}