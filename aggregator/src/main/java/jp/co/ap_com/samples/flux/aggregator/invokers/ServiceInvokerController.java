package jp.co.ap_com.samples.flux.aggregator.invokers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ap_com.samples.flux.aggregator.configurations.AggregatorConfig;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceInvokerController {
    private final AggregatorConfig aggregatorConfig;
    private final TimeFeedService timeFeedService;

    @GetMapping()
    public Mono<String> rest(Model model) {
        return Mono.zip(timeFeedService.getAggregatorFeed(), timeFeedService.getServerFeed())
        .map(tuple -> {
            model.addAttribute("titleVersion", aggregatorConfig.getTitleVersion());
            model.addAttribute("aggregator", tuple.getT1());
            model.addAttribute("feed", tuple.getT2());
            return "rest";
        });
    }    
}
