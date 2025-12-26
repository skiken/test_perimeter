package com.amundi.perimeter_engine.infrastructure.persistence.entity;

import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeDefId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "tenant_scope_def")
public class TenantScopeDef {


    @Getter
    @EmbeddedId
    private TenantScopeDefId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tenantId")
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "is_full_scope", nullable = false)
    private boolean fullScope;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Getter
    @Setter
    @Column(name = "last_Compute_Date")
    private Instant lastComputeDate;

    @Getter
    @Setter
    @Column(name = "last_Config_Date")
    private Instant lastConfigDate;


    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;



    protected TenantScopeDef() {
    }

    public TenantScopeDef(Tenant tenant, String scopeId, EntityType entityType,Instant lastComputeDate ) {
        this.id = new TenantScopeDefId(tenant.getTenantId(), scopeId);
        this.tenant = tenant;
        this.entityType = entityType;
        this.active = true;
        this.lastComputeDate=lastComputeDate;
    }


}
