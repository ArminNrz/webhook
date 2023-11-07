package com.webhook.core.partner;

import com.webhook.core.util.ULIDGenerator;
import com.webhook.core.vo.PartnerId;
import com.webhook.core.vo.PartnerName;
import com.webhook.core.vo.PartnerUrl;
import com.webhook.core.vo.WebhookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder(access = AccessLevel.PACKAGE)
public class Partner implements Serializable {
    private PartnerId partnerId;
    private PartnerName partnerName;
    private PartnerUrl partnerUrl;
    private WebhookStatus status;

    void initiate() {
        this.partnerId = new PartnerId(ULIDGenerator.generate());
    }

    void activate() {
        this.status = WebhookStatus.ACTIVE;
    }

    void suspended() {
        this.status = WebhookStatus.SUSPENDED;
    }

    void changeName(String newName) {
        this.partnerName = new PartnerName(newName);
    }

    public void changeUrl(String url) {
        this.partnerUrl = new PartnerUrl(url);
    }

    public PartnerId partnerId() {
        return partnerId;
    }

    public PartnerName partnerName() {
        return partnerName;
    }

    public PartnerUrl partnerUrl() {
        return partnerUrl;
    }

    public String url() {
        return partnerUrl.url();
    }

    public WebhookStatus status() {
        return status;
    }
}
