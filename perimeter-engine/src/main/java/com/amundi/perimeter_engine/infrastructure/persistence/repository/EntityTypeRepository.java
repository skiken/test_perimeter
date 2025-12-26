package com.amundi.perimeter_engine.infrastructure.persistence.repository;


import com.amundi.perimeter_engine.infrastructure.persistence.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Reference repository for entity types.
 */
public interface EntityTypeRepository
        extends JpaRepository<EntityType, String> {
}