package com.webhook.core.partner.event;

import com.webhook.core.partner.Partner;

import java.io.Serializable;
import java.time.ZonedDateTime;

public abstract class BasePartnerEvent implements Serializable {
    private final Partner partner;
    private final ZonedDateTime createdAt;

    public BasePartnerEvent(Partner partner, ZonedDateTime createdAt) {
        this.partner = partner;
        this.createdAt = createdAt;
    }

    public Partner partner() {
        return partner;
    }

    public ZonedDateTime createdAt() {
        return createdAt;
    }
}
