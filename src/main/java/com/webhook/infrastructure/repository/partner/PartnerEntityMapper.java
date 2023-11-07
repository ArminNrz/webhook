package com.webhook.infrastructure.repository.partner;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.PartnerMapper;
import com.webhook.infrastructure.repository.partner.entity.PartnerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartnerEntityMapper {

    private final PartnerMapper domainMapper;

    PartnerEntity toEntity(Partner partner) {
        PartnerEntity entity = new PartnerEntity();
        entity.setId(partner.partnerId().id());
        entity.setName(partner.partnerName().name());
        entity.setUrl(partner.url());
        entity.setStatus(partner.status());
        return entity;
    }

    public Partner toDomain(PartnerEntity entity) {
        return domainMapper.toDomain(entity.getId(), entity.getName(), entity.getUrl(), entity.getStatus());
    }
}
