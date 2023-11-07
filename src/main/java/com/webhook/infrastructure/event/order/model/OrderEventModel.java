package com.webhook.infrastructure.event.model;

import java.io.Serializable;
import java.util.UUID;

public record OrderEventModel(
        String orderId,
        UUID partnerId,
        String postTrackingCode
) implements Serializable {
}
