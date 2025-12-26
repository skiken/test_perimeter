package com.amundi.perimeter_engine.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "tenant")
public class Tenant {

    @Getter
    @Id
    @Column(name = "tenant_id", length = 50)
    private String tenantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updated_at;

    protected Tenant() {
    }

    public Tenant(String tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
        this.active = true;
    }

}