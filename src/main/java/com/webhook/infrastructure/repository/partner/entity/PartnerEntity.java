package com.webhook.infrastructure.repository.partner.entity;

import com.webhook.core.vo.WebhookStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "partner")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PartnerEntity implements Serializable {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private WebhookStatus status;

    @CreationTimestamp
    private ZonedDateTime created;

    @UpdateTimestamp
    private ZonedDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnerEntity that = (PartnerEntity) o;
        return id.equals(that.id) && name.equals(that.name) && url.equals(that.url) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, status);
    }
}
