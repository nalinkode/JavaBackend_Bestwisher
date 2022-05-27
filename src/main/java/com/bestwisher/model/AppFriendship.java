package com.bestwisher.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_friendship", schema = "app")
public class AppFriendship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long requestFromUser;
	private Long requestToUser;
	private boolean isRequestAccepted;
	private boolean isFriend;
	private Date createdDate;
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRequestAccepted() {
		return isRequestAccepted;
	}

	public void setRequestAccepted(boolean isRequestAccepted) {
		this.isRequestAccepted = isRequestAccepted;
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

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}


}
