package com.amundi.perimeter_engine.application.service.external;

import java.util.Map;

/**
 * Contexte d'exécution d’un service de calcul.
 * Contient le service + ses paramètres résolus.
 */
public record ScopeServiceExecutionContext(
        String serviceName,
        String endpoint,
        Map<String, String> parameters
) {
}