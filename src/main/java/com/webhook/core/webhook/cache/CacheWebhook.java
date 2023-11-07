package com.webhook.core.webhook;

import com.webhook.core.vo.CacheId;
import com.webhook.core.vo.PartnerId;
import com.webhook.core.vo.PartnerUrl;
import com.webhook.core.webhook.common.BaseWebhookDomain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder(access = AccessLevel.PRIVATE)
public class CacheWebhook extends BaseWebhookDomain {
    private final CacheId cacheId;
    private final PartnerId partnerId;
    private final PartnerUrl partnerUrl;
    private final int tryCount;
    private int backoffTimeMs;
    private final String body;


    @Override
    public String toJson() {
        return null;
    }
}
