package jp.co.ap_com.samples.flux.aggregator.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "backend")
public class BackendConfig {
    @NotEmpty
    private String timefeedHost;
    private String timefeedBase;    
}
