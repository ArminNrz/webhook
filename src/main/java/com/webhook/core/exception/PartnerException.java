package com.webhook.core.exception;

public class PartnerException extends RuntimeException {
    public PartnerException(String message) {
        super(message);
    }

    public PartnerException(String message, Throwable cause) {
        super(message, cause);
    }
}
