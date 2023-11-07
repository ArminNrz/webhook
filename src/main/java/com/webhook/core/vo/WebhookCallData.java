package com.webhook.core.webhook.common;

import java.io.Serializable;

public record WebhookCallData(
        String responseText,
        int responseStatus,
        boolean success
) implements Serializable {
}
