package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeConfig;
import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeConfigId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for scope calculation pipeline configuration.
 */
public interface TenantScopeConfigRepository
        extends JpaRepository<TenantScopeConfig, TenantScopeConfigId> {

    /**
     * Retrieve ordered calculation steps for a scope.
     */
    List<TenantScopeConfig>
    findByTenantIdAndScopeIdAndActiveTrueOrderByRulePriorityAsc(
            String tenantId,
            String scopeId
    );


    /**
     * Delete all steps of a scope (admin / reconfiguration use case).
     */
    void deleteByTenantIdAndScopeId(String tenantId, String scopeId);



}