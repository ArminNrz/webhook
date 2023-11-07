package com.webhook.core.webhook.order;

import com.webhook.core.vo.OrderId;
import com.webhook.core.vo.PartnerId;
import com.webhook.core.webhook.common.BaseWebhookDomain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder(access = AccessLevel.PACKAGE)
public class Order extends BaseWebhookDomain {
    private OrderId orderId;
    private PartnerId partnerId;
    private String postTrackingCode;

    @Override
    public String toJson() {
        return "{" +
                "\"orderId\": \"" + orderId.id() + "\"," +
                "\"partnerId\": \"" + partnerId.id() + "\"," +
                "\"postTrackingCode\": \"" + postTrackingCode + "\"" +
                '}';
    }

    public PartnerId partnerId() {
        return partnerId;
    }
}
