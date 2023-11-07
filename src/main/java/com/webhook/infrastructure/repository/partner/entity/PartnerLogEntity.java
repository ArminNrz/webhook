package com.webhook.infrastructure.repository.partner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "partner_log")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PartnerLogEntity implements Serializable {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "partner_entity_id")
    private PartnerEntity partnerEntity;

    @Column(name = "destination_url", nullable = false)
    private String destinationUrl;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "response_status_code")
    private Integer responseStatusCode;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "created")
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerLogEntity that = (PartnerLogEntity) o;
        return id.equals(that.id) && partnerEntity.equals(that.partnerEntity) && destinationUrl.equals(that.destinationUrl) && requestBody.equals(that.requestBody) && responseBody.equals(that.responseBody) && responseStatusCode.equals(that.responseStatusCode) && success.equals(that.success) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partnerEntity, destinationUrl, requestBody, responseBody, responseStatusCode, success, created);
    }
}
