package com.webhook.infrastructure.repository.partner.jpa;

import com.webhook.infrastructure.repository.partner.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerJpaRepository extends JpaRepository<PartnerEntity, String> {
}
