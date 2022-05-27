package com.bestwisher.dto;

public class AppPostLikeDto {

	private Long id;
	private boolean isLike;
	private Long userId;
	private Long postId;

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

}
