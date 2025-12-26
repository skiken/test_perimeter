package com.amundi.perimeter_engine.application.service.engine;

import com.amundi.perimeter_engine.application.service.external.HttpScopeClient;
import com.amundi.perimeter_engine.application.service.external.ScopeClient;
import com.amundi.perimeter_engine.application.service.external.ScopeServiceExecutionContext;
import org.springframework.stereotype.Component;

/**
 * Factory de clients d’appel de services.
 */
@Component
public class ScopeClientFactory {
    public ScopeClient getClient(ScopeServiceExecutionContext context) {
        // extensible plus tard (Kafka, JMS, etc.)
        return new HttpScopeClient();
    }
}
