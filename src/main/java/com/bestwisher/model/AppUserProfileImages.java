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
@Table(name = "app_user_profile_images", schema = "app")
public class AppUserProfileImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String profileImage;
	private boolean isProfileImage;
	private boolean isDefault;
	private String fileName;

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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public boolean isProfileImage() {
		return isProfileImage;
	}

	public void setProfileImage(boolean isProfileImage) {
		this.isProfileImage = isProfileImage;
	}
	
	public AppUserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(AppUserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
