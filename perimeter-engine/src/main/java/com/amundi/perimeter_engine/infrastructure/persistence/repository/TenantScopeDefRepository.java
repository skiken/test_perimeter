package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeDef;
import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantScopeDefId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for scope definitions.
 * Entry point for perimeter computation.
 */
public interface TenantScopeDefRepository
        extends JpaRepository<TenantScopeDef, TenantScopeDefId> {

    /**
     * Retrieve all active scopes for a tenant.
     */
    List<TenantScopeDef> findByTenantIdAndActiveTrue(String tenantId);


    /**
     * Retrieve a single active scope.
     */
    Optional<TenantScopeDef> findByTenantIdAndScopeIdAndActiveTrue(
            String tenantId,
            String scopeId
    );

    /**
     * Check if a scope exists and is active.
     */
    boolean existsByTenantIdAndScopeIdAndActiveTrue(
            String tenantId,
            String scopeId
    );
}
