package com.bestwisher.dto;

public class AppUserProfileAcademicDto {

	private Long id;
	private String schoolName;

	private String fromDate;

	private String toDate;

	private boolean isHide;

	private Long userId;

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

	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}
