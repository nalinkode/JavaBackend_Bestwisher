package com.bestwisher.dto;

import java.util.Date;

public class AppFriendshipDto extends AppUserProfileDto {

	private Long friendshipId;
	private Long requestFromUser;
	private Long requestToUser;
	private boolean isRequestAccepted;
	private boolean isFriend;
	private Date createdDate;
	private Date updatedDate;

	public Long getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(Long friendshipId) {
		this.friendshipId = friendshipId;
	}

	public Long getRequestFromUser() {
		return requestFromUser;
	}

	public void setRequestFromUser(Long requestFromUser) {
		this.requestFromUser = requestFromUser;
	}

	public Long getRequestToUser() {
		return requestToUser;
	}

	public void setRequestToUser(Long requestToUser) {
		this.requestToUser = requestToUser;
	}

	public boolean isRequestAccepted() {
		return isRequestAccepted;
	}

	public void setRequestAccepted(boolean isRequestAccepted) {
		this.isRequestAccepted = isRequestAccepted;
	}

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
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

}
