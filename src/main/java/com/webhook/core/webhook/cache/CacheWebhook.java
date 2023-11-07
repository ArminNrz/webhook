package com.webhook.core.webhook.cache;

import com.webhook.core.partner.Partner;
import com.webhook.core.util.ULIDGenerator;
import com.webhook.core.vo.CacheId;
import com.webhook.core.vo.PartnerId;
import com.webhook.core.vo.PartnerUrl;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder(access = AccessLevel.PRIVATE)
public class CacheWebhook implements Serializable {
    private final CacheId cacheId;
    private final PartnerId partnerId;
    private PartnerUrl partnerUrl;
    private BackoffPolicy backoffPolicy;
    private final String body;

    public static CacheWebhook init(Partner partner, int tryCount, String body) {
        return CacheWebhook.builder()
                .cacheId(new CacheId(ULIDGenerator.generate()))
                .partnerId(partner.partnerId())
                .partnerUrl(partner.partnerUrl())
                .backoffPolicy(BackoffPolicy.of(tryCount))
                .body(body)
                .build();
    }

    public static CacheWebhook of(String id, String partnerId, String partnerUrl, int tryCount, String body) {
        return CacheWebhook.builder()
                .cacheId(new CacheId(id))
                .partnerId(new PartnerId(partnerId))
                .partnerUrl(new PartnerUrl(partnerUrl))
                .backoffPolicy(BackoffPolicy.of(tryCount))
                .body(body)
                .build();
    }

    public void increaseTryCount() {
        int tryCount = backoffPolicy().tryCount();
        tryCount ++;
        this.backoffPolicy = BackoffPolicy.of(tryCount);
    }

    public void updateUrl(Partner partner) {
        this.partnerUrl = partner.partnerUrl();
    }

    public CacheId cacheId() {
        return cacheId;
    }

    public PartnerId partnerId() {
        return partnerId;
    }

    public PartnerUrl partnerUrl() {
        return partnerUrl;
    }

    public BackoffPolicy backoffPolicy() {
        return backoffPolicy;
    }

    public String body() {
        return body;
    }
}
