package com.webhook.application.input.partner.mapper;

import com.webhook.infrastructure.rest.partner.query.PartnerResponse;
import com.webhook.core.partner.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerApplicationMapper {
    public PartnerResponse toResponse(Partner partner) {
        return new PartnerResponse(
                partner.partnerId().id(),
                partner.partnerName().name(),
                partner.url(),
                partner.status()
        );
    }
}
