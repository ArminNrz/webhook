package com.webhook.infrastructure.rest.exception;

import java.io.Serializable;

public record ErrorDTO(String code, String message) implements Serializable {
}
