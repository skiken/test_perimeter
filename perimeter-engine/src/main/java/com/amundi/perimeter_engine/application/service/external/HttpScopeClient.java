package com.amundi.perimeter_engine.application.service.external;

import java.util.List;

/**
 * Client HTTP REST.
 * (l’implémentation HTTP réelle viendra plus tard).
 */
public class HttpScopeClient implements ScopeClient {
    @Override
    public List<String> fetchIds(ScopeServiceExecutionContext context) {
        // TODO : implémentation RestTemplate / WebClient
        return List.of();
    }
}