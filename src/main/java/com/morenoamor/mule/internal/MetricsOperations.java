package com.morenoamor.mule.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_PLAIN;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.morenoamor.metrics.MetricsManager;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;


public class MetricsOperations {

  @Alias("counter")
//   @MediaType(value = ANY, strict = false)
  public void counter(
      @Config MetricsConfiguration configuration,
      @Connection MetricsConnection connection,
      @DisplayName("Metric name") @Summary("Name of the counter metric") String name,
      @DisplayName("Increment") @Summary("Amount the counter will be increased") @Optional(defaultValue = "1") double amount,
      @DisplayName("Tags") @Summary("Additional metric tags") @Optional Map<String,String> tags
  ) {
      // connection.recordCounter(name, amount, tags);
      MetricsManager.recordCounter(name, amount, tags);
  }

  @Alias("timer")
//   @MediaType(value = ANY, strict = false)
  public void timer(
      @Config MetricsConfiguration configuration,
      @Connection MetricsConnection connection,
      @DisplayName("Metric name") @Summary("Name of the timer metric") String name,
      @DisplayName("Duration") @Summary("Duration of the measured timer") @Optional(defaultValue = "1") long duration,
      // @DisplayName("Unit") @Summary("Time unit of the duration") @Optional(defaultValue = TimeUnit.MILLISECONDS) TimeUnit unit,
      @DisplayName("Tags") @Summary("Additional metric tags") @Optional Map<String,String> tags
  ) {
      // connection.recordTimer(name, duration, TimeUnit.MILLISECONDS, tags);
      MetricsManager.recordTimer(name, duration, TimeUnit.MILLISECONDS, tags);
  }

  @Alias("gauge")
//   @MediaType(value = ANY, strict = false)
  public void gauge(
      @Config MetricsConfiguration configuration,
      @Connection MetricsConnection connection,
      @DisplayName("Metric name") @Summary("Name of the gauge metric") String name,
      @DisplayName("Amount") @Summary("Amount the gauge will be set to") long amount,
      @DisplayName("Tags") @Summary("Additional metric tags") @Optional Map<String,String> tags
  ) {
      // connection.recordGauge(name, amount, tags);
      MetricsManager.recordGauge(name, amount, tags);
  }

  @Alias("scrapeMetrics")
  @MediaType(value = APPLICATION_PLAIN, strict = true)
  public String scrapeMetrics(
      @Config MetricsConfiguration configuration,
      @Connection MetricsConnection connection
  ) {
      return MetricsManager.scrape();
  }

  // /**
  //  * Example of an operation that uses the configuration and a connection instance to perform some action.
  //  */
  // @MediaType(value = ANY, strict = false)
  // public String retrieveInfo(@Config MetricsConfiguration configuration, @Connection MetricsConnection connection){
  //   return "Using Configuration [" + configuration.getConfigId() + "] with Connection id [" + connection.getId() + "]";
  // }

  // /**
  //  * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
  //  */
  // @MediaType(value = ANY, strict = false)
  // public String sayHi(String person) {
  //   return buildHelloMessage(person);
  // }

  // /**
  //  * Private Methods are not exposed as operations
  //  */
  // private String buildHelloMessage(String person) {
  //   return "Hello " + person + "!!!";
  // }
}
