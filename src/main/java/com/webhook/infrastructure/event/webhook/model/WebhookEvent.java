package com.webhook.infrastructure.event.webhook;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.log.LogRequest;
import com.webhook.core.partner.log.LogResponse;
import com.webhook.core.util.TimeUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

@Getter
@Setter
public class WebhookEvent extends ApplicationEvent {
    private Partner partner;
    private LogRequest logRequest;
    private LogResponse logResponse;
    private ZonedDateTime created;

    public WebhookEvent(Object source, Partner partner, String requestBody, ResponseEntity<String> responseEntity) {
        super(source);
        this.partner = partner;
        this.logRequest = new LogRequest(partner.url(), requestBody);
        this.logResponse = new LogResponse(responseEntity.getBody(), responseEntity.getStatusCode().value());
        this.created = TimeUtils.now();
    }

    public WebhookEvent(Object source, Partner partner, String requestBody, Throwable throwable) {
        super(source);
        this.partner = partner;
        this.logRequest = new LogRequest(partner.url(), requestBody);
        this.logResponse = new LogResponse(throwable.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
        this.created = TimeUtils.now();
    }
}
