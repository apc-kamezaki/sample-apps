package jp.co.ap_com.samples.flux.timefeed;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import lombok.Value;

@Value
@Component
public class ServerConfig {
    private final String hostname = ((Supplier<String>) () -> {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }).get();
}
