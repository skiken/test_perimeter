package com.amundi.perimeter_engine.infrastructure.persistence.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record TenantAssetScopeId(
        String tenantId,
        String scopeId,
        String assetNum
) implements Serializable {

    public TenantAssetScopeId(String tenantId, String scopeId, String assetNum) {
        this.tenantId = tenantId;
        this.scopeId = scopeId;
        this.assetNum = assetNum;
    }

    public static TenantAssetScopeId of(String tenantId, String scopeId, String assetNum) {
        return new TenantAssetScopeId(tenantId, scopeId, assetNum);
    }
}