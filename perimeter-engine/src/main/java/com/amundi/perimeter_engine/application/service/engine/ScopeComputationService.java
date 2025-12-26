package com.amundi.perimeter_engine.application.service.engine;

import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantAssetScope;
import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeConfig;
import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeDef;
import com.amundi.perimeter_engine.infrastructure.persistence.repository.TenantAssetScopeRepository;
import com.amundi.perimeter_engine.infrastructure.persistence.repository.TenantScopeConfigRepository;
import com.amundi.perimeter_engine.infrastructure.persistence.repository.TenantScopeDefRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Orchestrateur principal du calcul de périmètre.
 * <p>
 * Responsabilités :
 * - Charger la configuration active
 * - Orchestrer les appels aux services
 * - Agréger les IDs
 * - Persister le résultat
 * - Auditer le calcul
 */
@Service
public class ScopeComputationService {


    private final TenantScopeDefRepository scopeDefRepository;
    private final TenantScopeConfigRepository scopeConfigRepository;
    private final TenantAssetScopeRepository assetScopeRepository;
    private final ScopeServiceResolver scopeServiceResolver;
    private final ScopeClientFactory clientFactory;


    public ScopeComputationService(
            TenantScopeDefRepository scopeDefRepository,
            TenantScopeConfigRepository scopeConfigRepository,
            TenantAssetScopeRepository assetScopeRepository,
            ScopeServiceResolver scopeServiceResolver,
            ScopeClientFactory clientFactory

    ) {
        this.scopeDefRepository = scopeDefRepository;
        this.scopeConfigRepository = scopeConfigRepository;
        this.assetScopeRepository = assetScopeRepository;
        this.scopeServiceResolver = scopeServiceResolver;
        this.clientFactory = clientFactory;

    }

    /**
     * Point d’entrée principal.
     * Input : tenantId uniquement.
     */
    public Set<String> computeScopes(String tenantId) {
        var scopeDefs = scopeDefRepository.findByTenantIdAndActiveTrue(tenantId);
        Set<String> result = new HashSet<>();
        for (var scopeDef : scopeDefs) {
            if (isScopeUpToDate(scopeDef)) {
                result.addAll(loadExistingAssets(scopeDef));
            } else {
                result.addAll(recomputeScope(scopeDef));
            }

        }
        return result;

    }

    /**
     * Vérifie si le scope est à jour.
     */
    private boolean isScopeUpToDate(TenantScopeDef scope) {
        if (scope.getLastComputeDate() == null) {
            return false;
        }
        return !scope.getLastComputeDate()
                .isBefore(scope.getLastConfigDate());
    }

    /**
     * Chargement direct depuis TENANT_ASSETS_SCOPE.
     */
    private Set<String> loadExistingAssets(TenantScopeDef scope) {
        return new HashSet<>(assetScopeRepository
                .findAssetNumsByTenantAndScope(
                        scope.getId().tenantId(),
                        scope.getId().scopeId()
                ));
    }

    /**
     * Recalcul complet du scope.
     */
    private Set<String> recomputeScope(TenantScopeDef scope) {
        List<TenantScopeConfig> scopeConfigs =
                scopeConfigRepository.findByTenantIdAndScopeIdAndActiveTrueOrderByRulePriorityAsc(
                        scope.getId().tenantId(),
                        scope.getId().scopeId()
                );
        Set<String> aggregatedIds = new HashSet<>();
        for (TenantScopeConfig scopeConfig : scopeConfigs) {
            var executionContext = scopeServiceResolver.resolve(scopeConfig);
            var client = clientFactory.getClient(executionContext);
            var ids = client.fetchIds(executionContext);
            aggregatedIds.addAll(ids);
        }
        // remplacement atomique du scope
        assetScopeRepository.deleteByTenantIdAndScopeId(
                scope.getId().tenantId(),
                scope.getId().scopeId()
        );
        persistAssets(scope, aggregatedIds);
        scope.setLastComputeDate(OffsetDateTime.now().toInstant());
        scopeDefRepository.save(scope);
        return aggregatedIds;
    }

    /**
     * Persistance des assets calculés.
     */
    private void persistAssets(TenantScopeDef scope, Set<String> assetNums) {
        for (String assetNum : assetNums) {
            assetScopeRepository.save(
                    TenantAssetScope.of(
                            scope.getId().tenantId(),
                            scope.getId().scopeId(),
                            assetNum,
                            Instant.now()

                    )
            );
        }
    }
}


