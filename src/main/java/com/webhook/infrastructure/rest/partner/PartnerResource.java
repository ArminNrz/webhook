package com.webhook.infrastructure.rest.partner;

import com.webhook.infrastructure.rest.partner.command.PartnerCreateCommand;
import com.webhook.infrastructure.rest.partner.query.PartnerResponse;
import com.webhook.application.input.partner.PartnerApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhook/partner")
@Slf4j
@RequiredArgsConstructor
public class PartnerResource {

    private final PartnerApplicationService applicationService;

    @PostMapping
    public ResponseEntity<PartnerResponse> create(@Valid @RequestBody PartnerCreateCommand createCommand) {
        var response = applicationService.create(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PartnerResponse>> getAll(Pageable pageable) {
        var page = applicationService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
