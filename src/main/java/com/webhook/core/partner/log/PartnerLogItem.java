package com.webhook.core.partner.log;

import com.webhook.core.util.ULIDGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@ToString
@Builder(access = AccessLevel.PRIVATE)
public class PartnerLogItem implements Serializable {
    private final String logId;
    private final LogRequest logRequest;
    private final LogResponse logResponse;
    private final boolean success;
    private final ZonedDateTime created;

    public static PartnerLogItem of(LogRequest logRequest, LogResponse logResponse, ZonedDateTime created) {
        return PartnerLogItem.builder()
                .logId(ULIDGenerator.generate())
                .logRequest(logRequest)
                .logResponse(logResponse)
                .success(logResponse.responseStatus().is2xxSuccessful())
                .created(created)
                .build();
    }

    public static PartnerLogItem of(String logId, LogRequest logRequest, LogResponse logResponse, ZonedDateTime created) {
        return PartnerLogItem.builder()
                .logId(logId)
                .logRequest(logRequest)
                .logResponse(logResponse)
                .success(logResponse.responseStatus().is2xxSuccessful())
                .created(created)
                .build();
    }

    public String logId() {
        return logId;
    }

    public LogRequest logRequest() {
        return logRequest;
    }

    public LogResponse logResponse() {
        return logResponse;
    }

    public boolean success() {
        return success;
    }

    public ZonedDateTime created() {
        return created;
    }
}
