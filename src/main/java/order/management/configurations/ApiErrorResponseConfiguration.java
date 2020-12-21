package order.management.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ApiErrorResponseConfiguration {

    @Bean
    public ResourceBundleMessageSource messageSource() {
       ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
       rs.setBasename("error-messages");
       rs.setDefaultEncoding("UTF-8");
       rs.setUseCodeAsDefaultMessage(true);
       return rs;
    }
}
