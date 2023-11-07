package com.webhook.core.partner.log;

import com.webhook.core.vo.PartnerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerLogDomainServiceImpl implements PartnerLogDomainService {

    private final PartnerLogRepository repository;

    @Override
    public Optional<PartnerLog> findByPartnerId(PartnerId partnerId, Pageable pageable) {
        return repository.findByPartnerId(partnerId, pageable);
    }

    @Override
    public void create(PartnerLog partnerLog, PartnerLogItem logItem) {
        partnerLog.create(logItem);
        repository.save(partnerLog);
    }
}
