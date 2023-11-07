package com.webhook.infrastructure.service.requestsender;

import com.webhook.core.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestSenderAdapter implements RequestSender {

    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> send(Partner partner, String transferJsonObject) {
        var url = partner.url();
        log.debug("Try to call webhook with url: {}, body: {}", url, transferJsonObject);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(url, new HttpEntity<>(transferJsonObject, headers), String.class);
    }
}
