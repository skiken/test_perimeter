package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeRuleParam;
import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeRuleParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * Repository for calculation rule parameters.
 */
public interface TenantScopeRuleParamRepository
        extends JpaRepository<TenantScopeRuleParam, TenantScopeRuleParamId> {

    /**
     * Retrieve all parameters for a given rule.
     */
    List<TenantScopeRuleParam> findByTenantIdAndScopeIdAndRuleId(
            String tenantId,
            String scopeId,
            String ruleId
    );


    /**
     * Delete all parameters for a given rule.
     */
    void deleteByTenantIdAndScopeIdAndRuleId(
            String tenantId,
            String scopeId,
            String ruleId
    );
}