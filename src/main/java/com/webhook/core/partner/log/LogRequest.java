package com.webhook.core.partner.log;

import java.io.Serializable;

public record LogRequest(
        String destinationUrl,
        String requestBody
) implements Serializable {
}
