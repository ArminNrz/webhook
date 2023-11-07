package com.webhook.core.partner.event;

import com.webhook.core.partner.Partner;

import java.time.ZonedDateTime;

public class PartnerActivateEvent extends BasePartnerEvent {
    public PartnerActivateEvent(Partner partner, ZonedDateTime createdAt) {
        super(partner, createdAt);
    }
}
