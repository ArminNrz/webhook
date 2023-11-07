package com.webhook.core.webhook.common;

import java.io.Serializable;

public abstract class BaseWebhookDomain implements Serializable {
    public abstract String toJson();
}
