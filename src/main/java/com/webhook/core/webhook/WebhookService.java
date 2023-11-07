package com.webhook.core.webhook;

import com.webhook.core.partner.Partner;
import com.webhook.core.webhook.common.BaseWebhookDomain;

public interface WebhookService<I extends BaseWebhookDomain> {
    void sendFirstAttempt(Partner partner, I transferObject);
}
