package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository for Tenant persistence.
 * Provides CRUD operations and tenant validation helpers.
 */
public interface TenantRepository extends JpaRepository<Tenant, String> {

    /**
     * Used by API & engine to validate an active tenant.
     */
    Optional<Tenant> findByTenantIdAndActiveTrue(String tenantId);


    /**
     * Fast existence check without loading the entity.
     */
    boolean existsByTenantIdAndActiveTrue(String tenantId);
}