package com.webhook.application.input.rest.partner.command;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record PartnerCreateCommand(
        @NotBlank
        String name,
        @NotBlank
        String url
) implements Serializable {
}
