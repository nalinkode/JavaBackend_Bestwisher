package com.bestwisher.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_post_like", schema = "app")
public class AppPostLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isLike;
	private Long userId;
	private Long postId;
	private Date postLikedDate;

	@ManyToMany()
	@JsonManagedReference
	private Set<AppUserPost> post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Set<AppUserPost> getPost() {
		return post;
	}

	public void setPost(Set<AppUserPost> post) {
		this.post = post;
	}

	public Date getPostLikedDate() {
		return postLikedDate;
	}

	public void setPostLikedDate(Date postLikedDate) {
		this.postLikedDate = postLikedDate;
	}
}
