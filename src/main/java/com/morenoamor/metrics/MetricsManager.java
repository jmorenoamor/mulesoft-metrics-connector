package com.morenoamor.metrics;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;


public class MetricsManager {

    public static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    private static Iterable<Tag> zipTags(Map<String,String> tags) {
        return tags.entrySet().stream().map(e -> Tag.of(e.getKey(), e.getValue())).collect(Collectors.toList());
    }


    public static void recordCounter(String name, double amount, Map<String,String> tags) {
        if (tags == null) {
            registry.counter(name).increment(amount);
        } else {
            registry.counter(name, zipTags(tags)).increment(amount);
        }
    }


    public static void recordTimer(String name, long amount, TimeUnit unit, Map<String,String> tags) {
        if (tags == null) {
            registry.timer(name).record(amount, unit);
        } else {
            registry.timer(name, zipTags(tags)).record(amount, unit);
        }
    }


    public static void recordGauge(String name, long amount, Map<String,String> tags) {
        if (tags == null) {
            registry.gauge(name, amount);
        } else {
            registry.gauge(name, zipTags(tags), amount);
        }
    }


    public static String scrape() {
        return registry.scrape();
    }
}
