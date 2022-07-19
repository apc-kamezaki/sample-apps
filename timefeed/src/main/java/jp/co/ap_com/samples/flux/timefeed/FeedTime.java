package jp.co.ap_com.samples.flux.timefeed;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FeedTime {
    private long time;
    private String version;
    private String hostname;
}
