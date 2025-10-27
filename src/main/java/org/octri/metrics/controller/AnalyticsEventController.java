package org.octri.metrics.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.octri.authentication.server.security.AuthenticationUserDetails;
import org.octri.authentication.server.security.SecurityHelper;
import org.octri.authentication.server.security.repository.UserRepository;
import org.octri.metrics.domain.AnalyticsEvent;
import org.octri.metrics.domain.Interaction;
import org.octri.metrics.repository.AnalyticsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link AnalyticsEvent} objects.
 */
@RestController
public class AnalyticsEventController {

	private static final Log log = LogFactory.getLog(AnalyticsEventController.class);

	@Autowired
	private AnalyticsEventRepository repository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * @param event the analytics event to log
	 * @return the response entity
	 */
	@PostMapping(path = "/data/analytics_event", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logAnalyticsEvent(@RequestBody AnalyticsEvent event) {
		SecurityHelper securityHelper = new SecurityHelper(SecurityContextHolder.getContext());
		AuthenticationUserDetails userDetails = securityHelper.authenticationUserDetails();

		if (userDetails != null) {
			event.setUser(userRepository.getReferenceById(userDetails.getUserId()));
			List<Interaction> interactions = event.getInteractions();
			for (Interaction i : interactions) {
				i.setAnalyticsEvent(event);
			}
			repository.save(event);
		} else {
			// Ajax requests triggered during logout can arrive after the user is logged out
			log.warn("Discarding anonymous analytics event");
		}

		return ResponseEntity.ok("{\"status\": \"ok\"}");
	}

}
