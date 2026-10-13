package org.octri.metrics.domain;

import java.time.Instant;

import org.octri.common.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a user's interaction with an element on the page.
 *
 * Note: Not all element interactions are recorded, only those matching specific
 * classes. See __bindEvents__ in
 * interactor.js for details.
 */
@Entity
public class Interaction extends AbstractEntity {

	private static final long serialVersionUID = -5670494104059658146L;

	/**
	 * Two types of interactions are distinguished, interactions and conversions,
	 * where a conversion is an interaction where the user performs a desired
	 * action.
	 */
	public enum InteractionType {
		/**
		 * A standard interaction.
		 */
		INTERACTION,
		/**
		 * A conversion interaction.
		 */
		CONVERSION;
	}

	/**
	 * the analytics event this interaction is associated with
	 */
	@NotNull
	@ManyToOne
	private AnalyticsEvent analyticsEvent;

	/**
	 * the client timestamp of the interaction
	 */
	private Instant clientTimestamp;

	/**
	 * the x coordinate of the client
	 */
	@Column(name = "client_x")
	private Integer clientX;

	/**
	 * the y coordinate of the client
	 */
	@Column(name = "client_y")
	private Integer clientY;

	/**
	 * the content of the target element
	 */
	private String content;

	/**
	 * the event name
	 */
	private String event;

	/**
	 * the screen x coordinate
	 */
	@Column(name = "screen_x")
	private Integer screenX;

	/**
	 * the screen y coordinate
	 */
	@Column(name = "screen_y")
	private Integer screenY;

	/**
	 * the target element's classes
	 */
	private String targetClasses;

	/**
	 * the target element's tag
	 */
	private String targetTag;

	/**
	 * the type of interaction
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private InteractionType type;

	/**
	 * @return the AnalyticsEvent
	 */
	public AnalyticsEvent getAnalyticsEvent() {
		return analyticsEvent;
	}

	/**
	 * @param analyticsEvent the event for this interaction
	 */
	public void setAnalyticsEvent(AnalyticsEvent analyticsEvent) {
		this.analyticsEvent = analyticsEvent;
	}

	/**
	 * @return the client timestamp
	 */
	public Instant getClientTimestamp() {
		return clientTimestamp;
	}

	/**
	 * @param clientTimestamp the client timestamp
	 */
	public void setClientTimestamp(Instant clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	/**
	 * @return the x coordinate of the interaction
	 */
	public Integer getClientX() {
		return clientX;
	}

	/**
	 * @param clientX the x coordinate of the interaction
	 */
	public void setClientX(Integer clientX) {
		this.clientX = clientX;
	}

	/**
	 * @return the y coordinate of the interaction
	 */
	public Integer getClientY() {
		return clientY;
	}

	/**
	 * @param clientY the y coordinate of the interaction
	 */
	public void setClientY(Integer clientY) {
		this.clientY = clientY;
	}

	/**
	 * @return the content of the target element
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content of the target element
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the event name
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event name
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the screen x coordinate
	 */
	public Integer getScreenX() {
		return screenX;
	}

	/**
	 * @param screenX the screen x coordinate
	 */
	public void setScreenX(Integer screenX) {
		this.screenX = screenX;
	}

	/**
	 * @return the screen y coordinate
	 */
	public Integer getScreenY() {
		return screenY;
	}

	/**
	 * @param screenY the screen y coordinate
	 */
	public void setScreenY(Integer screenY) {
		this.screenY = screenY;
	}

	/**
	 * @return the target classes
	 */
	public String getTargetClasses() {
		return targetClasses;
	}

	/**
	 * @param targetClasses the target classes
	 */
	public void setTargetClasses(String targetClasses) {
		this.targetClasses = targetClasses;
	}

	/**
	 * @return String the target tag
	 */
	public String getTargetTag() {
		return targetTag;
	}

	/**
	 * @param targetTag the target tag
	 */
	public void setTargetTag(String targetTag) {
		this.targetTag = targetTag;
	}

	/**
	 * @return the type of interaction
	 */
	public InteractionType getType() {
		return type;
	}

	/**
	 * @param type the type of interaction
	 */
	public void setType(InteractionType type) {
		this.type = type;
	}

}
