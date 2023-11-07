package com.webhook.infrastructure.repository.partner;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.log.LogRequest;
import com.webhook.core.partner.log.LogResponse;
import com.webhook.core.partner.log.PartnerLog;
import com.webhook.core.partner.log.PartnerLogItem;
import com.webhook.infrastructure.repository.partner.entity.PartnerEntity;
import com.webhook.infrastructure.repository.partner.entity.PartnerLogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartnerLogEntityMapper {

    private final PartnerEntityMapper partnerEntityMapper;

    PartnerLogEntity toEntity(PartnerLog partnerLog) {
        PartnerLogEntity entity = new PartnerLogEntity();
        entity.setId(partnerLog.logId());
        PartnerEntity partnerEntity = new PartnerEntity();
        partnerEntity.setId(partnerLog.partnerId().id());
        entity.setPartnerEntity(partnerEntity);
        entity.setDestinationUrl(partnerLog.url());
        entity.setRequestBody(partnerLog.requestBody());
        entity.setResponseBody(partnerLog.responseBody());
        entity.setResponseStatusCode(partnerLog.responseStatusCode());
        entity.setSuccess(partnerLog.responseIsSuccess());
        entity.setCreated(partnerLog.created());
        return entity;
    }

    PartnerLog toDomain(Page<PartnerLogEntity> logEntityPage, PartnerEntity partnerEntity) {
        Partner partner = partnerEntityMapper.toDomain(partnerEntity);
        Page<PartnerLogItem> logItemPage = logEntityPage.map(this::toLogItem);
        return PartnerLog.of(partner, logItemPage);
    }

    private PartnerLogItem toLogItem(PartnerLogEntity logEntity) {
        return PartnerLogItem.of(
                logEntity.getId(),
                new LogRequest(logEntity.getDestinationUrl(), logEntity.getRequestBody()),
                new LogResponse(logEntity.getResponseBody(), HttpStatusCode.valueOf(logEntity.getResponseStatusCode())),
                logEntity.getCreated()
        );
    }
}
