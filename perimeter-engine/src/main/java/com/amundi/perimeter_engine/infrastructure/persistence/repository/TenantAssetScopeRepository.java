package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantAssetScope;
import com.amundi.perimeter_engine.infrastructure.persistence.id.TenantAssetScopeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Repository storing computed scope results.
 * High read/write volume – performance sensitive.
 */
public interface TenantAssetScopeRepository
        extends JpaRepository<TenantAssetScope, TenantAssetScopeId> {

    /**
     * Check if a scope already has computed assets.
     */
    boolean existsByTenantIdAndScopeId(String tenantId, String scopeId);


    /**
     * Retrieve all assets for a scope.
     */
    List<TenantAssetScope> findByTenantIdAndScopeId(
            String tenantId,
            String scopeId
    );

    /**
     * Delete previous computation before full recompute.
     */
    void deleteByTenantIdAndScopeId(
            String tenantId,
            String scopeId
    );


    /**
     * Incremental cleanup based on validity.
     */
    void deleteByIdTenantIdAndIdScopeIdAndValidToBefore(
            String tenantId,
            String scopeId,
            OffsetDateTime date
    );


    /**
     * Retrieve all assets number for a scope.
     */
    List<String> findAssetNumsByTenantAndScope(String tenantId, String scopeId);





}