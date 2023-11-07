package com.webhook.application.input.rest.exception;

import java.io.Serializable;

public record ErrorDTO(String code, String message) implements Serializable {
}
