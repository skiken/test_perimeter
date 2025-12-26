package com.amundi.perimeter_engine.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Reference table describing supported entity types (ASSET, POSITION, PORTFOLIO…).
 */
@Entity
@Table(name = "entity_type")
public class EntityType {

    @Getter
    @Id
    @Column(name = "entity_type_code", length = 50)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updated_at;
    protected EntityType() {
    }

    public EntityType(String code) {
        this.code = code;
    }



}



















