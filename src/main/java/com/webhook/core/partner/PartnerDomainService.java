package com.webhook.core.partner;

import com.webhook.core.partner.event.PartnerActivateEvent;
import com.webhook.core.partner.event.PartnerCreateEvent;
import com.webhook.core.partner.event.PartnerSuspendEvent;
import com.webhook.core.partner.event.PartnerUpdateEvent;
import com.webhook.core.vo.PartnerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerDomainService {
    PartnerCreateEvent create(Partner partner);
    PartnerActivateEvent activate(Partner partner);
    PartnerSuspendEvent suspend(Partner partner);
    PartnerUpdateEvent update(Partner partner, String name, String url);
    Partner findById(PartnerId partnerId);
    Page<Partner> findAll(Pageable pageable);
}
