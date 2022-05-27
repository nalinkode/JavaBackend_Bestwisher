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
@Table(name = "app_user_cover_images", schema = "app")
public class AppUserCoverImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String coverImage;
	private boolean isCoverImage;
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

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public boolean isCoverImage() {
		return isCoverImage;
	}

	public void setCoverImage(boolean isCoverImage) {
		this.isCoverImage = isCoverImage;
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
