package org.dapr.demo.service.Dapr;

import io.dapr.client.DaprClientBuilder;
import io.dapr.client.DaprPreviewClient;
import io.dapr.client.domain.ConfigurationItem;
import io.dapr.client.domain.GetConfigurationRequest;
import io.dapr.client.domain.SubscribeConfigurationRequest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationService {

    private static final String CONFIG_STORE_NAME = "configstore";

    private DaprPreviewClient client;

    public ConfigurationService() {
        this.client = (new DaprClientBuilder()).buildPreviewClient();
    }

    /**
     * Gets configuration for a single key.
     * @param key String
     */
    public ConfigurationItem getConfigurationForaSingleKey(String key) {
        try {
            Mono<ConfigurationItem> item = this.client.getConfiguration(CONFIG_STORE_NAME, key);
            return item.block();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Gets configurations for varibale no. of arguments.
     *
     * @param keys
     */
    public ArrayList<ConfigurationItem> getConfigurationsUsingVarargs(String... keys) {
        try {
            Mono<List<ConfigurationItem>> items =
                    client.getConfiguration(CONFIG_STORE_NAME, keys);
            ArrayList<ConfigurationItem> configurations = new ArrayList<>();
            for (ConfigurationItem item : items.block()) {
                configurations.add(item);
            }
            return configurations;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
