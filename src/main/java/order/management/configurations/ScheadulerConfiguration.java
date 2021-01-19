package order.management.configurations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(
        name = "scheduling.enabled",
        havingValue = "true",
        matchIfMissing = true
)
@EnableScheduling
@Configuration
public class ScheadulerConfiguration {

}
