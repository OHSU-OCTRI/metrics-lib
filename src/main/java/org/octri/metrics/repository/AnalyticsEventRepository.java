package org.octri.metrics.repository;

import org.octri.metrics.domain.AnalyticsEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link AnalyticsEvent} objects.
 */
public interface AnalyticsEventRepository extends CrudRepository<AnalyticsEvent, Long> {

}