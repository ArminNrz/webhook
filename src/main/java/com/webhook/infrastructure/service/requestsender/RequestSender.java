package com.webhook.infrastructure.service.requestsender;

import com.webhook.core.partner.Partner;
import org.springframework.http.ResponseEntity;

public interface RequestSender {
    ResponseEntity<String> send(Partner partner, String transferJsonObject);
}
