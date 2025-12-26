package com.amundi.perimeter_engine.application.service.external;

import java.util.Collection;

/**
 * Contrat générique d’un client de service de calcul.
 */
public interface ScopeClient {
    Collection<String> fetchIds(ScopeServiceExecutionContext context);
}