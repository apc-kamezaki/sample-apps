package jp.co.ap_com.samples.flux.aggregator.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean(name = "timefeedWebClient")
    public WebClient timefeedWebClient(BackendConfig config) {
        return WebClient.create(config.getTimefeedHost());
    }
}
