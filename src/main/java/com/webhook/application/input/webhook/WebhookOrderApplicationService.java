package com.webhook.application.input.webhook;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.PartnerDomainService;
import com.webhook.core.webhook.WebhookService;
import com.webhook.core.webhook.order.Order;
import com.webhook.core.webhook.order.OrderDomainMapper;
import com.webhook.infrastructure.event.order.model.OrderEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebhookOrderApplicationService {

    private final OrderDomainMapper domainMapper;
    private final PartnerDomainService partnerDomainService;
    private final WebhookService<Order> webhookService;

    @Transactional
    public void processOrderEvent(OrderEventModel eventModel) {
        Order order = domainMapper.toOrderDomain(eventModel.orderId(), eventModel.partnerId(), eventModel.postTrackingCode());
        Partner partner = partnerDomainService.findById(order.partnerId());

        webhookService.sendFirstAttempt(partner, order);
    }
}
