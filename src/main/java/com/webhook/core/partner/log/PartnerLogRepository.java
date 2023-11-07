package com.webhook.core.partner.log;

import com.webhook.core.vo.PartnerId;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PartnerLogRepository {
    Optional<PartnerLog> findByPartnerId(PartnerId partnerId, Pageable pageable);
    void save(PartnerLog partnerLog);
}
