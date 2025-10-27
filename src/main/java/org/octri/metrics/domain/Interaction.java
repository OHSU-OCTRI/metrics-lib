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
 * Note: Not all element interactions are recorded, only those matching specific classes. See __bindEvents__ in
 * interactor.js for details.
 */
@Entity
public class Interaction extends AbstractEntity {

	private static final long serialVersionUID = -5670494104059658146L;

	/**
	 * Two types of interactions are distinguished, interactions and conversions, where a conversion is an interaction
	 * where the user performs a desired action.
	 */
	public enum InteractionType {
		INTERACTION, CONVERSION;
	}

	@NotNull
	@ManyToOne
	private AnalyticsEvent analyticsEvent;

	private Instant clientTimestamp;

	@Column(name = "client_x")
	private Integer clientX;

	@Column(name = "client_y")
	private Integer clientY;

	private String content;

	private String event;

	@Column(name = "screen_x")
	private Integer screenX;

	@Column(name = "screen_y")
	private Integer screenY;

	private String targetClasses;

	private String targetTag;

	@NotNull
	@Enumerated(EnumType.STRING)
	private InteractionType type;

	public AnalyticsEvent getAnalyticsEvent() {
		return analyticsEvent;
	}

	public void setAnalyticsEvent(AnalyticsEvent analyticsEvent) {
		this.analyticsEvent = analyticsEvent;
	}

	public Instant getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp(Instant clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	public Integer getClientX() {
		return clientX;
	}

	public void setClientX(Integer clientX) {
		this.clientX = clientX;
	}

	public Integer getClientY() {
		return clientY;
	}

	public void setClientY(Integer clientY) {
		this.clientY = clientY;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getScreenX() {
		return screenX;
	}

	public void setScreenX(Integer screenX) {
		this.screenX = screenX;
	}

	public Integer getScreenY() {
		return screenY;
	}

	public void setScreenY(Integer screenY) {
		this.screenY = screenY;
	}

	public String getTargetClasses() {
		return targetClasses;
	}

	public void setTargetClasses(String targetClasses) {
		this.targetClasses = targetClasses;
	}

	public String getTargetTag() {
		return targetTag;
	}

	public void setTargetTag(String targetTag) {
		this.targetTag = targetTag;
	}

	public InteractionType getType() {
		return type;
	}

	public void setType(InteractionType type) {
		this.type = type;
	}

}
