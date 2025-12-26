package com.amundi.perimeter_engine.infrastructure.persistence.entity;


import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeRuleParamId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "tenant_scope_rule_param")
public class TenantScopeRuleParam {

    @Getter
    @EmbeddedId
    private TenantScopeRuleParamId id;

    @Getter
    @Column(name = "param_value", nullable = false)
    private String paramValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updated_at;

    protected TenantScopeRuleParam() {
    }

    public TenantScopeRuleParam(TenantScopeRuleParamId id, String paramValue) {
        this.id = id;
        this.paramValue = paramValue;
    }
}