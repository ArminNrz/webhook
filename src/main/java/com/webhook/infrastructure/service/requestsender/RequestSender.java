package com.webhook.core.webhook;

import com.webhook.core.partner.Partner;
import org.springframework.http.ResponseEntity;

public interface RequestSender {
    ResponseEntity<String> send(Partner partner, String transferJsonObject);
}
