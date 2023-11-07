package com.webhook.infrastructure.rest.partner.query;

import com.webhook.core.vo.WebhookStatus;

import java.io.Serializable;

public record PartnerResponse(
        String id,
        String name,
        String url,
        WebhookStatus status
) implements Serializable {
}
