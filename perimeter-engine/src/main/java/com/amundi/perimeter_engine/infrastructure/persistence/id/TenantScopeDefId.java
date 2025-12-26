package com.amundi.perimeter_engine.infrastructure.persistence.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public record TenantScopeDefId(
        String tenantId,
        String scopeId
) implements Serializable {
}