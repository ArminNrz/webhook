package com.webhook.infrastructure.rest.partner.command;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record PartnerCreateCommand(
        @NotBlank
        String name,
        @NotBlank
        String url
) implements Serializable {
}
