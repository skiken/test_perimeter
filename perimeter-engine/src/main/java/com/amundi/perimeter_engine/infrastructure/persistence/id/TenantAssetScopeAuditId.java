package com.amundi.perimeter_engine.infrastructure.persistence.id;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record TenantAssetScopeAuditId(
        String tenantId,
        String scopeId,
        String AuditId
) implements Serializable {}