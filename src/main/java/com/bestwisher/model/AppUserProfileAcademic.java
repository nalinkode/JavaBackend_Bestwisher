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
@Table(name = "app_user_academic", schema = "app")
public class AppUserProfileAcademic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String schoolName;

	private String fromDate;

	private String toDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_profile_id")
	@JsonManagedReference
	private AppUserProfile userProfile;
	
	private boolean isHide;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	
	

}
