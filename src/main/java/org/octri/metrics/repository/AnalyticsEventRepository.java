package org.octri.metrics.repository;

import org.octri.metrics.domain.AnalyticsEvent;
import org.springframework.data.repository.CrudRepository;

public interface AnalyticsEventRepository extends CrudRepository<AnalyticsEvent, Long> {

}