package com.webhook.application.input.partner;

import com.webhook.core.partner.log.PartnerLog;
import com.webhook.core.partner.log.PartnerLogDomainMapper;
import com.webhook.core.partner.log.PartnerLogDomainService;
import com.webhook.core.partner.log.PartnerLogItem;
import com.webhook.infrastructure.event.webhook.model.WebhookEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PartnerApplicationLogService {

    private final PartnerLogDomainService domainService;
    private final PartnerLogDomainMapper domainMapper;

    @Transactional
    public void persist(WebhookEvent event) {
        PartnerLogItem logItem = domainMapper.createLogItem(event.getLogRequest(), event.getLogResponse(), event.getCreated());
        PartnerLog partnerLog = domainMapper.create(event.getPartner());
        domainService.create(partnerLog, logItem);
    }
}
