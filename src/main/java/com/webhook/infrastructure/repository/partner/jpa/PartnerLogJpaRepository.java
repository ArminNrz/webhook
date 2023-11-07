package com.webhook.infrastructure.repository.partner.jpa;

import com.webhook.infrastructure.repository.partner.entity.PartnerLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerLogJpaRepository extends JpaRepository<PartnerLogEntity, String> {
    Page<PartnerLogEntity> findAllByPartnerEntityId(String partnerId, Pageable pageable);
}
