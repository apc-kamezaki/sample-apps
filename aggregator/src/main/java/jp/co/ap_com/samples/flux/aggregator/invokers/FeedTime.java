package jp.co.ap_com.samples.flux.aggregator.invokers;

import lombok.Data;

@Data
public class FeedTime {
    private long time;
    private String version;
    private String hostname;    
}
