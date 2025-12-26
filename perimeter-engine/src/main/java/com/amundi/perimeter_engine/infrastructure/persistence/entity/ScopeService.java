package com.amundi.perimeter_engine.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;


@Entity
@Table(name = "scope_service")
public class ScopeService {

    @Getter
    @Id
    @Column(name = "scope_service_id",  length = 50)
    private Long serviceId;

    @Getter
    @Column(name = "service_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "entity_type", referencedColumnName = "entity_type_code")
    private EntityType producedEntityTypeCode;

    @Getter
    @Column(name = "service_endpoint")
    private String endpoint;


    @Column(name = "timeout_ms")
    private Integer timeoutMs;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updated_at;

    public ScopeService(Long serviceId) {
        this.serviceId = serviceId;
    }
}



