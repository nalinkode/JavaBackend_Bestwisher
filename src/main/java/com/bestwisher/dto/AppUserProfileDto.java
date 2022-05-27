package com.bestwisher.dto;

import java.util.Set;

public class AppUserProfileDto {

	private Long id;
	private String currentDesignation;
	private String homeCity;
	private String currentCity;
	private String profileImage;
	private String coverImage;
	private Long userId;
	private String userName;

	private Set<AppUserProfileAcademicDto> academic;

	private Set<AppUserProfileWorkDto> workPlace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<AppUserProfileAcademicDto> getAcademic() {
		return academic;
	}

	public void setAcademic(Set<AppUserProfileAcademicDto> academic) {
		this.academic = academic;
	}

	public Set<AppUserProfileWorkDto> getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(Set<AppUserProfileWorkDto> workPlace) {
		this.workPlace = workPlace;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
