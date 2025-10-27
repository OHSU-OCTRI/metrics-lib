package org.octri.metrics.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.octri.authentication.server.security.entity.User;
import org.octri.common.domain.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a user interaction with a page.
 */
@Entity
public class AnalyticsEvent extends AbstractEntity {

	private static final long serialVersionUID = -4934575154300288678L;

	/**
	 * the user interacting with the page
	 */
	@NotNull
	@ManyToOne
	private User user;

	/**
	 * the total duration of the event in seconds
	 */
	private Integer totalDurationSeconds;

	/**
	 * the number of active seconds during the event
	 */
	private Integer activeSeconds;

	/**
	 * the end inner height
	 */
	private Integer endInnerHeight;

	/**
	 * the end inner width
	 */
	private Integer endInnerWidth;

	/**
	 * the end name
	 */
	private String endName;

	/**
	 * the end outer height
	 */
	private Integer endOuterHeight;

	/**
	 * the end outer width
	 */
	private Integer endOuterWidth;

	/**
	 * the interactions for the event
	 */
	@OneToMany(mappedBy = "analyticsEvent", cascade = CascadeType.ALL)
	private List<Interaction> interactions = new ArrayList<Interaction>();

	/**
	 * the language
	 */
	private String language;

	/**
	 * the load time
	 */
	private Instant loadTime;

	/**
	 * the page location
	 */
	private String pageLocation;

	/**
	 * the referrer
	 */
	private String referrer;

	/**
	 * the platform
	 */
	private String platform;

	/**
	 * the port
	 */
	private String port;

	/**
	 * the start inner height
	 */
	private Integer startInnerHeight;

	/**
	 * the start inner width
	 */
	private Integer startInnerWidth;

	/**
	 * the start outer height
	 */
	private Integer startOuterHeight;

	/**
	 * the start outer width
	 */
	private Integer startOuterWidth;

	/**
	 * the unload time
	 */
	private Instant unloadTime;

	/**
	 * the user agent
	 */
	private String userAgent;

	/**
	 * the app version
	 */
	private String appVersion;

	/**
	 * @return the user interacting with the page
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user interacting with the page
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the event duration
	 */
	public Integer getTotalDurationSeconds() {
		return totalDurationSeconds;
	}

	/**
	 * @param totalDurationSeconds the event duration
	 */
	public void setTotalDurationSeconds(Integer totalDurationSeconds) {
		this.totalDurationSeconds = totalDurationSeconds;
	}

	/**
	 * @return the number of active seconds
	 */
	public Integer getActiveSeconds() {
		return activeSeconds;
	}

	/**
	 * @param activeSeconds the number of active seconds
	 */
	public void setActiveSeconds(Integer activeSeconds) {
		this.activeSeconds = activeSeconds;
	}

	/**
	 * @return the end inner height
	 */
	public Integer getEndInnerHeight() {
		return endInnerHeight;
	}

	/**
	 * @param endInnerHeight the end inner height
	 */
	public void setEndInnerHeight(Integer endInnerHeight) {
		this.endInnerHeight = endInnerHeight;
	}

	/**
	 * @return the end inner width
	 */
	public Integer getEndInnerWidth() {
		return endInnerWidth;
	}

	/**
	 * @param endInnerWidth the end inner width
	 */
	public void setEndInnerWidth(Integer endInnerWidth) {
		this.endInnerWidth = endInnerWidth;
	}

	/**
	 * @return the end name
	 */
	public String getEndName() {
		return endName;
	}

	/**
	 * @param endName the end name
	 */
	public void setEndName(String endName) {
		this.endName = endName;
	}

	/**
	 * @return the end outer height
	 */
	public Integer getEndOuterHeight() {
		return endOuterHeight;
	}

	/**
	 * @param endOuterHeight the end outer height
	 */
	public void setEndOuterHeight(Integer endOuterHeight) {
		this.endOuterHeight = endOuterHeight;
	}

	/**
	 * @return the end outer width
	 */
	public Integer getEndOuterWidth() {
		return endOuterWidth;
	}

	/**
	 * @param endOuterWidth the end outer width
	 */
	public void setEndOuterWidth(Integer endOuterWidth) {
		this.endOuterWidth = endOuterWidth;
	}

	/**
	 * @return the interactions for the event
	 */
	public List<Interaction> getInteractions() {
		return interactions;
	}

	/**
	 * @param interactions the interactions for the event
	 */
	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the load time
	 */
	public Instant getLoadTime() {
		return loadTime;
	}

	/**
	 * @param loadTime the load time
	 */
	public void setLoadTime(Instant loadTime) {
		this.loadTime = loadTime;
	}

	/**
	 * @return the page location
	 */
	public String getPageLocation() {
		return pageLocation;
	}

	/**
	 * @param pageLocation the page location
	 */
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}

	/**
	 * @return the referrer
	 */
	public String getReferrer() {
		return referrer;
	}

	/**
	 * @param referrer the referrer
	 */
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the start inner height
	 */
	public Integer getStartInnerHeight() {
		return startInnerHeight;
	}

	/**
	 * @param startInnerHeight the start inner height
	 */
	public void setStartInnerHeight(Integer startInnerHeight) {
		this.startInnerHeight = startInnerHeight;
	}

	/**
	 * @return the start inner width
	 */
	public Integer getStartInnerWidth() {
		return startInnerWidth;
	}

	/**
	 * @param startInnerWidth the start inner width
	 */
	public void setStartInnerWidth(Integer startInnerWidth) {
		this.startInnerWidth = startInnerWidth;
	}

	/**
	 * @return the start outer height
	 */
	public Integer getStartOuterHeight() {
		return startOuterHeight;
	}

	/**
	 * @param startOuterHeight the start outer height
	 */
	public void setStartOuterHeight(Integer startOuterHeight) {
		this.startOuterHeight = startOuterHeight;
	}

	/**
	 * @return the start outer width
	 */
	public Integer getStartOuterWidth() {
		return startOuterWidth;
	}

	/**
	 * @param startOuterWidth the start outer width
	 */
	public void setStartOuterWidth(Integer startOuterWidth) {
		this.startOuterWidth = startOuterWidth;
	}

	/**
	 * @return the unload time
	 */
	public Instant getUnloadTime() {
		return unloadTime;
	}

	/**
	 * @param unloadTime the unload time
	 */
	public void setUnloadTime(Instant unloadTime) {
		this.unloadTime = unloadTime;
	}

	/**
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the app version
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * @param appVersion the app version
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}