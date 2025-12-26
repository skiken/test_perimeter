package com.amundi.perimeter_engine.infrastructure.persistence.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public record TenantScopeConfigId(
        String tenantId,
        String scopeId,
        String ruleId
) implements Serializable {



}