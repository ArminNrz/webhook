package com.webhook.core.webhook.order;

import com.webhook.core.vo.OrderId;
import com.webhook.core.vo.PartnerId;
import org.springframework.stereotype.Component;

@Component
public class OrderDomainMapper {

    public Order toOrderDomain(String orderId, String partnerId, String postTrackingCode) {
        return Order.builder()
                .orderId(new OrderId(orderId))
                .partnerId(new PartnerId(partnerId))
                .postTrackingCode(postTrackingCode)
                .build();
    }
}
