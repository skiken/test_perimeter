package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.ScopeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for calculation services registry.
 */
public interface ScopeServiceRepository
        extends JpaRepository<ScopeService, String> {

    /**
     * Resolve an active service by id.
     */
    Optional<ScopeService> findByServiceIdAndActiveTrue(String serviceId);

    /**
     * List all active services (admin / monitoring).
     */
    List<ScopeService> findByActiveTrue();
}