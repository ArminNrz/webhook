package com.webhook.core.partner;

import com.webhook.core.exception.PartnerNotFoundException;
import com.webhook.core.partner.event.PartnerActivateEvent;
import com.webhook.core.partner.event.PartnerCreateEvent;
import com.webhook.core.partner.event.PartnerSuspendEvent;
import com.webhook.core.partner.event.PartnerUpdateEvent;
import com.webhook.core.util.TimeUtils;
import com.webhook.core.vo.PartnerId;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnerDomainServiceImpl implements PartnerDomainService {

    private final PartnerRepository repository;

    @Override
    public PartnerCreateEvent create(Partner partner) {
        partner.initiate();
        log.debug("Try to save partner: {}", partner);
        repository.save(partner);
        return new PartnerCreateEvent(partner, TimeUtils.now());
    }

    @Override
    public PartnerActivateEvent activate(Partner partner) {
        partner.activate();
        log.debug("Try to activate partner: {}", partner);
        repository.update(partner);
        return new PartnerActivateEvent(partner, TimeUtils.now());
    }

    @Override
    public PartnerSuspendEvent suspend(Partner partner) {
        partner.suspended();
        log.debug("Try to suspend partner: {}", partner);
        repository.update(partner);
        return new PartnerSuspendEvent(partner, TimeUtils.now());
    }

    @Override
    public PartnerUpdateEvent update(Partner partner, String name, String url) {
        if (StringUtils.isNotBlank(name))
            partner.changeName(name);
        if (StringUtils.isNotBlank(url))
            partner.changeUrl(url);
        log.debug("Try to update partner: {}", partner);
        repository.update(partner);
        return new PartnerUpdateEvent(partner, TimeUtils.now());
    }

    @Override
    public Partner findById(PartnerId partnerId) {
        log.debug("Try to find partner with partner id: {}", partnerId);
        return repository.findById(partnerId).orElseThrow(PartnerNotFoundException::new);
    }

    @Override
    public Page<Partner> findAll(Pageable pageable) {
        log.debug("Try to find all partners with page: {}", pageable);
        return repository.findAll(pageable);
    }
}
