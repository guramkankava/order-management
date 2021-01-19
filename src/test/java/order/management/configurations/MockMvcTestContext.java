package order.management.configurations;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import order.management.service.OrderService;

@ComponentScan(lazyInit = true)
@TestConfiguration
public class MockMvcTestContext {

    @Bean
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
        return Mockito.mock(ResourceBundleMessageSource.class);
    }

    @Bean
    public OrderService orderService() {
        return Mockito.mock(OrderService.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
