package hello.order.guage;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StockConfigV2 {

    @Bean
    public MeterBinder stockSize(OrderService orderService) {
        return r -> Gauge.builder("my.stock", orderService, s -> {
            log.info("stock gauge call");
            return s.getStock().get();
        }).register(r);
    }
}
