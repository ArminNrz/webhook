package com.webhook.core.partner.log;

import com.webhook.core.partner.Partner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PartnerLogDomainMapper {

    public PartnerLog create(Partner partner) {
        return PartnerLog.builder()
                .partner(partner)
                .build();
    }

    public PartnerLogItem createLogItem(LogRequest logRequest, LogResponse logResponse, ZonedDateTime created) {
        return PartnerLogItem.of(logRequest, logResponse, created);
    }
}
