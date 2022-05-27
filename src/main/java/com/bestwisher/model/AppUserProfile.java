package com.bestwisher.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_user_profile", schema = "app")
public class AppUserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String currentProfileImage;
	private String currentCoverImage;
	private String currentDesignation;
	private String homeCity;
	private String currentCity;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonManagedReference
	private AppUser user;

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<AppUserProfileImages> userProfileImages;

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<AppUserCoverImages> userCoverImages;

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<AppUserProfileAcademic> academic;

	@OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<AppUserProfileWork> workPlace;

	private Date createdDate;
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentProfileImage() {
		return currentProfileImage;
	}

	public void setCurrentProfileImage(String currentProfileImage) {
		this.currentProfileImage = currentProfileImage;
	}

	public String getCurrentCoverImage() {
		return currentCoverImage;
	}

	public void setCurrentCoverImage(String currentCoverImage) {
		this.currentCoverImage = currentCoverImage;
	}

	public String getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
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

	public Set<AppUserProfileAcademic> getAcademic() {
		return academic;
	}

	public void setAcademic(Set<AppUserProfileAcademic> academic) {
		this.academic = academic;
	}

	public Set<AppUserProfileWork> getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(Set<AppUserProfileWork> workPlace) {
		this.workPlace = workPlace;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<AppUserProfileImages> getUserProfileImages() {
		return userProfileImages;
	}

	public void setUserProfileImages(List<AppUserProfileImages> userProfileImages) {
		this.userProfileImages = userProfileImages;
	}

	public List<AppUserCoverImages> getUserCoverImages() {
		return userCoverImages;
	}

	public void setUserCoverImages(List<AppUserCoverImages> userCoverImages) {
		this.userCoverImages = userCoverImages;
	}

}
