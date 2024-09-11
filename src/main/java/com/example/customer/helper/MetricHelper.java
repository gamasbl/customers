package com.example.customer.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component
public class MetricHelper {
	private final MeterRegistry meterRegistry;

    @Autowired
    public MetricHelper(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementSuccess(String endpoint) {
        meterRegistry.counter("requests_total", Tags.of("endpoint", endpoint, "status", "success")).increment();
    }

    public void incrementFailure(String endpoint) {
        meterRegistry.counter("requests_total", Tags.of("endpoint", endpoint, "status", "failure")).increment();
    }
}
