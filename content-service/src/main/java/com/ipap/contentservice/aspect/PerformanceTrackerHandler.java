package com.ipap.contentservice.aspect;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class PerformanceTrackerHandler implements ObservationHandler<Observation.Context> {

    /*
     * Track execution time with onStart and OnStop methods of ObservationHandler<Observation.Context> interface
     */
    @Override
    public void onStart(Observation.Context context) {
        log.info("Execution started {}", context.getName());
        context.put("time", System.currentTimeMillis());
        //ObservationHandler.super.onStart(context);
    }

    @Override
    public void onError(Observation.Context context) {
        log.error("Error occured {}", Objects.requireNonNull(context.getError()).getMessage());
        //ObservationHandler.super.onError(context);
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onStop(Observation.Context context) {
        long executionTime = System.currentTimeMillis() - context.getOrDefault("time", 0L);
        log.info("Execution stopped, time elapsed: {} ms", executionTime);
        //ObservationHandler.super.onStop(context);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}