package com.webhook.infrastructure.service.helper;

import com.webhook.core.partner.Partner;
import com.webhook.core.partner.PartnerDomainService;
import com.webhook.core.partner.PartnerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebhookFirstCallBackHelperTest {

    @Autowired
    WebhookFirstCallBackHelper callBackHelper;

    @Autowired
    PartnerMapper partnerMapper;
    @Autowired
    PartnerDomainService partnerDomainService;

    @Test
    void calBackTest() {
        RuntimeException throwable = new RuntimeException("My Exception");
        Partner partner = partnerMapper.create("p2", "http://localhost:8081/test");
        partnerDomainService.create(partner);
        callBackHelper.callBack(null, throwable, null, partner);
    }
}