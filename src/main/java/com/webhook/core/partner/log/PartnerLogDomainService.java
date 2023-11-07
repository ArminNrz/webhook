package com.webhook.core.partner.log;

import com.webhook.core.vo.PartnerId;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PartnerLogDomainService {
    Optional<PartnerLog> findByPartnerId(PartnerId partnerId, Pageable pageable);
    void create(PartnerLog partnerLog, PartnerLogItem logItem);
}
