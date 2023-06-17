package com.ipap.contentservice.config;

import com.ipap.contentservice.aspect.PerformanceTrackerHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @AutoConfiguration
// @ConditionalOnClass(ObservedAspect.class)
@Configuration
public class ObservationAspectConfig {

    @Bean
    // @ConditionalOnMissingBean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry,
                                         PerformanceTrackerHandler performanceTrackerHandler){
        observationRegistry.observationConfig().observationHandler(performanceTrackerHandler);
        return new ObservedAspect(observationRegistry);
    }
}
