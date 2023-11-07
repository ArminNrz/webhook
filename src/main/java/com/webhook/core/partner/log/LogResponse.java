package com.webhook.core.partner.log;

import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

public record LogResponse(
        String responseBody,
        HttpStatusCode responseStatus
) implements Serializable {
}
