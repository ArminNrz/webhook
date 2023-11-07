package com.webhook.core.partner;

import com.webhook.core.vo.PartnerId;
import com.webhook.core.vo.PartnerName;
import com.webhook.core.vo.PartnerUrl;
import com.webhook.core.vo.WebhookStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PartnerMapper {

    public Partner create(String name, String url) {
        return Partner.builder()
                .partnerName(new PartnerName(name))
                .status(WebhookStatus.SUSPENDED)
                .partnerUrl(new PartnerUrl(url))
                .build();
    }

    public Partner toDomain(String id, String name, String url, WebhookStatus status) {
        return Partner.builder()
                .partnerId(new PartnerId(id))
                .partnerName(new PartnerName(name))
                .partnerUrl(new PartnerUrl(url))
                .status(status)
                .build();
    }
}
