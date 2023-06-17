package com.ipap.contentservice.config;

import com.ipap.contentservice.client.ArticleClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    
    @Bean
    public ArticleClient articleClient() {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/api")
            .defaultHeader("SPRING-BOOT-VERSION", "3.1.0")
            // The below strategy is needed in order to view masked headers (Not for production).
            .exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(c -> c.defaultCodecs().enableLoggingRequestDetails(true))
                    .build())
            .build();
        
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(ArticleClient.class);
    }
}
