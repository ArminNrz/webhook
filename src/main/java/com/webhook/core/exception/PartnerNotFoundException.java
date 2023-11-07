package com.webhook.core.exception;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException() {
        super("Partner not found !");
    }
}
