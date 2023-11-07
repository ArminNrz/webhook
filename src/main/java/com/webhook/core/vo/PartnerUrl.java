package com.webhook.core.vo;


import com.webhook.core.exception.PartnerException;
import com.webhook.core.util.UrlUtils;
import io.micrometer.common.util.StringUtils;

import java.io.Serializable;

public record PartnerUrl(String url) implements Serializable {
    public PartnerUrl {
        if (StringUtils.isBlank(url)) {
            throw new PartnerException("Partner url is blank !");
        }

        if (!UrlUtils.isUrlValid(url)) {
            throw new PartnerException("Partner url is not valid !");
        }
    }

}
