package com.bestwisher.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_user_work", schema = "app")
public class AppUserProfileWork {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String workPlaceName;
	
	private String designation;

	private String fromDate;

	private String toDate;
	
	private boolean isHide;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_profile_id")
	@JsonManagedReference
	private AppUserProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkPlaceName() {
		return workPlaceName;
	}

	public void setWorkPlaceName(String workPlaceName) {
		this.workPlaceName = workPlaceName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public AppUserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(AppUserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
