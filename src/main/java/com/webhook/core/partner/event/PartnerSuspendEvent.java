package com.webhook.core.partner.event;

import com.webhook.core.partner.Partner;

import java.time.ZonedDateTime;

public class PartnerSuspendEvent extends BasePartnerEvent {
    public PartnerSuspendEvent(Partner partner, ZonedDateTime createdAt) {
        super(partner, createdAt);
    }
}
