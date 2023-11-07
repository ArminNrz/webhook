package com.webhook.core.partner.log;

import com.webhook.core.partner.Partner;
import com.webhook.core.vo.PartnerId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.time.ZonedDateTime;

@ToString
@Builder(access = AccessLevel.PACKAGE)
public class PartnerLog implements Serializable {
    private final Partner partner;
    private PartnerLogItem logItem;
    private final Page<PartnerLogItem> partnerLogItems;

    public static PartnerLog of(Partner partner, Page<PartnerLogItem> partnerLogItems) {
        return PartnerLog.builder()
                .partner(partner)
                .partnerLogItems(partnerLogItems)
                .logItem(partnerLogItems.stream().findFirst().orElse(null))
                .build();
    }

    public Partner partner() {
        return partner;
    }

    public Page<PartnerLogItem> partnerLogItems() {
        if (ObjectUtils.isEmpty(partnerLogItems)) {
            Page.empty();
        }
        return this.partnerLogItems;
    }

    public void create(PartnerLogItem logItem) {
        this.logItem = logItem;
    }

    public String logId() {
        checkLog();
        return logItem.logId();
    }

    private void checkLog() {
        if (ObjectUtils.isEmpty(logItem)) {
            throw new ServiceException("Partner has no log item");
        }
    }

    public PartnerId partnerId() {
        if (ObjectUtils.isEmpty(this.partner)) {
            throw new ServiceException("Partner has not set !");
        }
        return partner.partnerId();
    }

    public String url() {
        checkLog();
        return logItem.logRequest().destinationUrl();
    }

    public String requestBody() {
        checkLog();
        return logItem.logRequest().requestBody();
    }

    public String responseBody() {
        checkLog();
        return logItem.logResponse().responseBody();
    }

    public ZonedDateTime created() {
        checkLog();
        return logItem.created();
    }

    public int responseStatusCode() {
        HttpStatusCode statusCode = httpStatusCode();
        return statusCode.value();
    }

    public boolean responseIsSuccess() {
        return httpStatusCode().is2xxSuccessful();
    }

    private HttpStatusCode httpStatusCode() {
        checkLog();
        var statusCode = logItem.logResponse().responseStatus();
        if (statusCode == null) {
            throw new ServiceException("Partner log status code is empty !");
        }
        return statusCode;
    }
}
