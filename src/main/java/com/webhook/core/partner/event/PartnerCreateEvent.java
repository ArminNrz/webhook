package com.webhook.core.partner.event;

import com.webhook.core.partner.Partner;

import java.time.ZonedDateTime;

public class PartnerCreateEvent extends BasePartnerEvent {
    public PartnerCreateEvent(Partner partner, ZonedDateTime createdAt) {
        super(partner, createdAt);
    }
}
