package com.webhook.infrastructure.event.order.model;

import java.io.Serializable;

public record OrderEventModel(
        String orderId,
        String partnerId,
        String postTrackingCode
) implements Serializable {
}
