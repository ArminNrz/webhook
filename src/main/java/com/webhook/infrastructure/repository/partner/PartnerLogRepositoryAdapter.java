package com.webhook.infrastructure.repository.partner;

import com.webhook.core.partner.log.PartnerLog;
import com.webhook.core.partner.log.PartnerLogRepository;
import com.webhook.core.vo.PartnerId;
import com.webhook.infrastructure.repository.partner.entity.PartnerEntity;
import com.webhook.infrastructure.repository.partner.entity.PartnerLogEntity;
import com.webhook.infrastructure.repository.partner.jpa.PartnerLogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerLogRepositoryAdapter implements PartnerLogRepository {

    private final PartnerLogJpaRepository jpaRepository;
    private final PartnerLogEntityMapper entityMapper;

    @Override
    public Optional<PartnerLog> findByPartnerId(PartnerId partnerId, Pageable pageable) {
        Page<PartnerLogEntity> logEntityPage = jpaRepository.findAllByPartnerEntityId(partnerId.id(), pageable);
        PartnerEntity partnerEntity = logEntityPage.getContent().stream()
                .filter(Objects::nonNull)
                .map(PartnerLogEntity::getPartnerEntity)
                .findFirst()
                .orElse(null);

        if (partnerEntity == null) {
            return Optional.empty();
        }

        PartnerLog partnerLog = entityMapper.toDomain(logEntityPage, partnerEntity);
        log.info("Found with partner id: {}, pageable: {}, partner logs: {}", partnerId, pageable, partnerLog);
        return Optional.of(partnerLog);
    }

    @Override
    public void save(PartnerLog partnerLog) {
        PartnerLogEntity entity = entityMapper.toEntity(partnerLog);
        jpaRepository.save(entity);
        log.info("Saved partner log entity: {}, for partner: {}", entity, partnerLog.partner());
    }
}
