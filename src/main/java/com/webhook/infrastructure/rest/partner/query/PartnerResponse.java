package com.webhook.application.input.rest.partner.query;

import com.webhook.core.vo.WebhookStatus;

import java.io.Serializable;
import java.util.UUID;

public record PartnerResponse(
        UUID id,
        String name,
        String url,
        WebhookStatus status
) implements Serializable {
}
