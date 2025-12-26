package com.amundi.perimeter_engine.infrastructure.persistence.id;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record TenantScopeRuleParamId(
        String tenantId,
        String scopeId,
        String ruleId,
        String paramName
) implements Serializable {
}