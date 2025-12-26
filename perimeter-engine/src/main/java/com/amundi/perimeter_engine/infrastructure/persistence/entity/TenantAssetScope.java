package com.amundi.perimeter_engine.infrastructure.persistence.entity;


import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantAssetScopeId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tenant_asset_scope")
public class TenantAssetScope {


    @EmbeddedId
    @Getter
    private TenantAssetScopeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tenantId")
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("scopeId")
    @JoinColumns({@JoinColumn(name = "tenant_id", referencedColumnName = "tenant_id", insertable = false, updatable = false), @JoinColumn(name = "scope_id", referencedColumnName = "scope_id")})
    private TenantScopeDef scopeDef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "valid_from")
    private Instant validFrom;

    @Column(name = "valid_to")
    private Instant validTo;

    @Column(name = "COMPUTE_DATE", nullable = false)
    private Instant computeDate;



    public TenantAssetScope(TenantAssetScopeId id, Instant computeDate) {
        this.id = id;
        this.computeDate = computeDate;
    }


    public static TenantAssetScope of(String tenantId, String scopeId, String assetNum, Instant computeDate) {
        return new TenantAssetScope(TenantAssetScopeId.of(tenantId, scopeId, assetNum),computeDate);
    }
}