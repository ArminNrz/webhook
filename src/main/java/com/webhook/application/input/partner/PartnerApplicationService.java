package com.webhook.application.input.partner;

import com.webhook.application.input.partner.mapper.PartnerApplicationMapper;
import com.webhook.core.partner.Partner;
import com.webhook.core.partner.PartnerDomainService;
import com.webhook.core.partner.PartnerMapper;
import com.webhook.core.partner.event.PartnerCreateEvent;
import com.webhook.infrastructure.rest.partner.command.PartnerCreateCommand;
import com.webhook.infrastructure.rest.partner.query.PartnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnerApplicationService {

    private final PartnerDomainService domainService;

    private final PartnerMapper partnerMapper;
    private final PartnerApplicationMapper applicationMapper;

    @Transactional
    public PartnerResponse create(PartnerCreateCommand createCommand) {
        Partner partner = partnerMapper.create(createCommand.name(), createCommand.url());
        PartnerCreateEvent event = domainService.create(partner);
        return applicationMapper.toResponse(event.partner());
    }

    @Transactional(readOnly = true)
    public Page<PartnerResponse> getAll(Pageable pageable) {
        return domainService.findAll(pageable).map(applicationMapper::toResponse);
    }
}
