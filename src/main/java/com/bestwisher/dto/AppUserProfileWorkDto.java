package com.bestwisher.dto;

public class AppUserProfileWorkDto {

	private Long id;
	private String workPlaceName;
	private String designation;

	private String fromDate;

	private String toDate;

	private boolean isHide;

	private Long userId;
	
	private boolean isCurrentDesignation;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isCurrentDesignation() {
		return isCurrentDesignation;
	}

	public void setCurrentDesignation(boolean isCurrentDesignation) {
		this.isCurrentDesignation = isCurrentDesignation;
	}

	
}
