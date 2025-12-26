package com.amundi.perimeter_engine.infrastructure.persistence.entity;


import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeConfigId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;


@Entity
@Table(name ="tenant_scope_config")
public class TenantScopeConfig {


    @Getter
    @EmbeddedId
    private TenantScopeConfigId id;

    @Getter
    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "scope_service_id", nullable = false, referencedColumnName = "scope_service_id")
    private ScopeService scopeService;

    @Column(name = "rule_priority", nullable = false)
    private Integer rulePriority;

    @Column(name = "is_active", nullable = false)
    private Boolean active=true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;


}
