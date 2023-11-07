package com.webhook.core.vo;

import com.webhook.core.exception.PartnerException;
import io.micrometer.common.util.StringUtils;

import java.io.Serializable;

public record PartnerName(String name) implements Serializable {
    public PartnerName {
        if (StringUtils.isBlank(name)) {
            throw new PartnerException("Partner name is blank !");
        }
    }
}
