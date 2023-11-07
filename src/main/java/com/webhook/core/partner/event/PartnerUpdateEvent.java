package com.webhook.core.partner.event;

import com.webhook.core.partner.Partner;

import java.time.ZonedDateTime;

public class PartnerUpdateEvent extends BasePartnerEvent {
    public PartnerUpdateEvent(Partner partner, ZonedDateTime createdAt) {
        super(partner, createdAt);
    }
}
