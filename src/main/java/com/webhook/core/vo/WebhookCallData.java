package com.webhook.core.vo;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public record WebhookCallData(
        String requestBody,
        String responseText,
        HttpStatus status,
        boolean success
) implements Serializable {
}
