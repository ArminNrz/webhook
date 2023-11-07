package com.webhook.infrastructure.service;

import com.webhook.core.partner.Partner;
import com.webhook.core.webhook.RequestSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestSenderAdapter implements RequestSender {

    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> send(Partner partner, String transferJsonObject) {
        var url = partner.url();
        log.debug("Try to call webhook with url: {}, body: {}", url, transferJsonObject);
        return restTemplate.postForEntity(url, new HttpEntity<>(transferJsonObject), String.class);
    }
}
