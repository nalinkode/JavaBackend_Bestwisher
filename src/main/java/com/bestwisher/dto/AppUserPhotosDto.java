package com.bestwisher.dto;

public class AppUserPhotosDto {

	private Long photoId;
	private String ImageUrl;
	private boolean isProfileImage;
	private String type;
	private Long profileId;
	private Long userId;

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public boolean isProfileImage() {
		return isProfileImage;
	}

	public void setProfileImage(boolean isProfileImage) {
		this.isProfileImage = isProfileImage;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
