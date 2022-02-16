package com.morenoamor.mule.internal;

import java.util.Map;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;


@Operations(MetricsOperations.class)
@ConnectionProviders(MetricsConnectionProvider.class)
public class MetricsConfiguration {

  @Parameter
  @Optional(defaultValue="mule")
  @DisplayName("Metrics prefix")
  @Summary("Prefix that will be added to all the metrics using this configuration")
  private String prefix;

  @Parameter
  @Optional
  @DisplayName("Common tags")
  @Summary("Tags that will be applied to all the metrics using this configuration")
  private Map<String,String> commonTags;

  public String getPrefix(){
    return prefix;
  }

  public Map<String,String> getCommonTags(){
    return commonTags;
  }

  public String getMetricName(String metric) {
    return metric == null ? metric : String.join("_", prefix, metric);
  }

}
