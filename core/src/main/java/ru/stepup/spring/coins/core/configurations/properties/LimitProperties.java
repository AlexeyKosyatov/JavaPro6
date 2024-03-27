package ru.stepup.spring.coins.core.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.limit")
public class LimitProperties {
    private RestTemplateProperties client;

    public RestTemplateProperties getClient() {
        return client;
    }

    @ConstructorBinding
    public LimitProperties(RestTemplateProperties client) {
        this.client = client;
    }
}
