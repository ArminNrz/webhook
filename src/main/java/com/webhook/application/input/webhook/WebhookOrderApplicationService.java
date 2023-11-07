package com.webhook.application.input.webhook;

import com.webhook.core.webhook.order.Order;
import com.webhook.core.webhook.order.OrderDomainMapper;
import com.webhook.infrastructure.event.model.OrderEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebhookApplicationService {

    private final OrderDomainMapper orderDomainMapper;

    @Transactional
    public void processOrderEvent(OrderEventModel eventModel) {
        Order order = orderDomainMapper.toOrderDomain(eventModel.orderId(), eventModel.partnerId(), eventModel.postTrackingCode());

    }
}
