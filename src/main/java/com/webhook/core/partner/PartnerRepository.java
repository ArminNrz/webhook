package com.webhook.core.partner;

import com.webhook.core.vo.PartnerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PartnerRepository {
    void save(Partner partner);
    void update(Partner partner);
    Optional<Partner> findById(PartnerId id);
    Page<Partner> findAll(Pageable pageable);
}
