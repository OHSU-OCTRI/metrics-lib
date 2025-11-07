package org.octri.metrics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for the Interactor JavaScript library.
 */
@ConfigurationProperties(prefix = "interactor")
public class InteractorProperties {

	/**
	 * CSS selector for interaction elements to monitor.
	 */
	private String interactionElement = ".accordion-button, .btn, .nav-link, .thumbnail";
	/**
	 * Whether to enable debug logging.
	 */
	private Boolean debug = false;
	/**
	 * Interval (in milliseconds) between logging events when there is no route
	 * change.
	 */
	private Integer logInterval = 60000;

	/**
	 * The CSS selector for interaction elements to monitor.
	 * 
	 * @return a string representing the CSS selector
	 */
	public String getInteractionElement() {
		return interactionElement;
	}

	/**
	 * Set the CSS selector for interaction elements to monitor.
	 * 
	 * @param interactionElement a string representing the CSS selector
	 */
	public void setInteractionElement(String interactionElement) {
		this.interactionElement = interactionElement;
	}

	/**
	 * Get whether debug logging is enabled.
	 * 
	 * @return true if debug logging is enabled, false otherwise
	 */
	public Boolean getDebug() {
		return debug;
	}

	/**
	 * Set whether debug logging is enabled.
	 * 
	 * @param debug true to enable debug logging, false otherwise
	 */
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	/**
	 * Get the interval (in milliseconds) between logging events when there is no
	 * route change.
	 * 
	 * @return the interval in milliseconds
	 */
	public Integer getLogInterval() {
		return logInterval;
	}

	/**
	 * Set the interval (in milliseconds) between logging events when there is no
	 * route change.
	 * 
	 * @param logInterval the interval in milliseconds
	 */
	public void setLogInterval(Integer logInterval) {
		this.logInterval = logInterval;
	}

}
