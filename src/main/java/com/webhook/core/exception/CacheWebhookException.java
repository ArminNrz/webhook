package com.webhook.core.exception;

public class CacheWebhookException extends RuntimeException {
    public CacheWebhookException(String message) {
        super(message);
    }

    public CacheWebhookException(String message, Throwable cause) {
        super(message, cause);
    }
}
