package com.webhook.infrastructure.repository.partner;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.PartnerRepository;
import com.webhook.core.vo.PartnerId;
import com.webhook.infrastructure.repository.partner.entity.PartnerEntity;
import com.webhook.infrastructure.repository.partner.jpa.PartnerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerRepositoryAdapter implements PartnerRepository {

    private final PartnerJpaRepository jpaRepository;
    private final PartnerEntityMapper entityMapper;

    @Override
    public void save(Partner partner) {
        PartnerEntity entity = entityMapper.toEntity(partner);
        jpaRepository.save(entity);
        log.info("Saved partner entity: {}", partner);
    }

    @Override
    public void update(Partner partner) {
        this.save(partner);
    }

    @Override
    public Optional<Partner> findById(PartnerId partnerId) {
        return jpaRepository.findById(partnerId.id()).map(entityMapper::toDomain);
    }

    @Override
    public Page<Partner> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(entityMapper::toDomain);
    }
}
