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

	@NotNull
	@ManyToOne
	private User user;

	private Integer totalDurationSeconds;

	private Integer activeSeconds;

	private Integer endInnerHeight;

	private Integer endInnerWidth;

	private String endName;

	private Integer endOuterHeight;

	private Integer endOuterWidth;

	@OneToMany(mappedBy = "analyticsEvent", cascade = CascadeType.ALL)
	private List<Interaction> interactions = new ArrayList<Interaction>();

	private String language;

	private Instant loadTime;

	private String pageLocation;

	private String referrer;

	private String platform;

	private String port;

	private Integer startInnerHeight;

	private Integer startInnerWidth;

	private Integer startOuterHeight;

	private Integer startOuterWidth;

	private Instant unloadTime;

	private String userAgent;

	private String appVersion;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getTotalDurationSeconds() {
		return totalDurationSeconds;
	}

	public void setTotalDurationSeconds(Integer totalDurationSeconds) {
		this.totalDurationSeconds = totalDurationSeconds;
	}

	public Integer getActiveSeconds() {
		return activeSeconds;
	}

	public void setActiveSeconds(Integer activeSeconds) {
		this.activeSeconds = activeSeconds;
	}

	public Integer getEndInnerHeight() {
		return endInnerHeight;
	}

	public void setEndInnerHeight(Integer endInnerHeight) {
		this.endInnerHeight = endInnerHeight;
	}

	public Integer getEndInnerWidth() {
		return endInnerWidth;
	}

	public void setEndInnerWidth(Integer endInnerWidth) {
		this.endInnerWidth = endInnerWidth;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public Integer getEndOuterHeight() {
		return endOuterHeight;
	}

	public void setEndOuterHeight(Integer endOuterHeight) {
		this.endOuterHeight = endOuterHeight;
	}

	public Integer getEndOuterWidth() {
		return endOuterWidth;
	}

	public void setEndOuterWidth(Integer endOuterWidth) {
		this.endOuterWidth = endOuterWidth;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Instant getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(Instant loadTime) {
		this.loadTime = loadTime;
	}

	public String getPageLocation() {
		return pageLocation;
	}

	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getStartInnerHeight() {
		return startInnerHeight;
	}

	public void setStartInnerHeight(Integer startInnerHeight) {
		this.startInnerHeight = startInnerHeight;
	}

	public Integer getStartInnerWidth() {
		return startInnerWidth;
	}

	public void setStartInnerWidth(Integer startInnerWidth) {
		this.startInnerWidth = startInnerWidth;
	}

	public Integer getStartOuterHeight() {
		return startOuterHeight;
	}

	public void setStartOuterHeight(Integer startOuterHeight) {
		this.startOuterHeight = startOuterHeight;
	}

	public Integer getStartOuterWidth() {
		return startOuterWidth;
	}

	public void setStartOuterWidth(Integer startOuterWidth) {
		this.startOuterWidth = startOuterWidth;
	}

	public Instant getUnloadTime() {
		return unloadTime;
	}

	public void setUnloadTime(Instant unloadTime) {
		this.unloadTime = unloadTime;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}