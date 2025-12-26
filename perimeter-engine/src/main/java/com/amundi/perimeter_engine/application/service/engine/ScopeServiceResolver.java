package com.amundi.perimeter_engine.application.service.engine;

import com.amundi.perimeter_engine.application.service.external.ScopeServiceExecutionContext;
import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeConfig;
import com.amundi.perimeter_engine.infrastructure.persistence.entity.TenantScopeRuleParam;
import com.amundi.perimeter_engine.infrastructure.persistence.repository.TenantScopeRuleParamRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;



/**
 * Résout un service de calcul prêt à être exécuté
 * (service + paramètres dynamiques).
 */
@Service
public class ScopeServiceResolver {
    private final TenantScopeRuleParamRepository ruleParamRepository;

    public ScopeServiceResolver(TenantScopeRuleParamRepository ruleParamRepository) {
        this.ruleParamRepository = ruleParamRepository;
    }

    public ScopeServiceExecutionContext resolve(TenantScopeConfig tenantScopeConfig) {
        var params = ruleParamRepository
                .findByTenantIdAndScopeIdAndRuleId(
                        tenantScopeConfig.getId().tenantId(),
                        tenantScopeConfig.getId().scopeId(),
                        tenantScopeConfig.getId().ruleId()
                )
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getId().paramName(),
                        TenantScopeRuleParam::getParamValue
                ));

        var service = tenantScopeConfig.getScopeService();
        return new ScopeServiceExecutionContext(
                service.getName(),
                service.getEndpoint(),
                params
        );
    }
}
