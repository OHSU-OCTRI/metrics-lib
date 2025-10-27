package org.octri.metrics.config;

import org.octri.metrics.controller.AnalyticsEventController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Autoconfiguration for the Metrics module.
 */
@AutoConfiguration
@EntityScan(basePackages = { "org.octri.metrics.domain" })
@EnableJpaRepositories(basePackages = "org.octri.metrics.repository")
@Import({ AnalyticsEventController.class })
public class MetricsAutoConfiguration {

}
