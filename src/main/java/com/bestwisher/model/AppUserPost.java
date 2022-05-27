package com.bestwisher.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_posts", schema = "app")
public class AppUserPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;
	private String imageUrl;
	
	private String feeling;
	private String location;
	private String tag;

	@ManyToOne()
	@JsonManagedReference
	private AppUser user;

	@ManyToMany(mappedBy = "post", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<AppPostLike> like;

	private Date createdDate;

	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser appUser) {
		this.user = appUser;
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

	public Set<AppPostLike> getLike() {
		return like;
	}

	public void setLike(Set<AppPostLike> like) {
		this.like = like;
	}
	
	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "AppUserPost [id=" + id + ", text=" + text + ", imageUrl=" + imageUrl + ", user=" + user
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
