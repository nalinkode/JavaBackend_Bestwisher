package com.bestwisher.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bestwisher.dto.AppPostDto;
import com.bestwisher.dto.AppPostLikeDto;
import com.bestwisher.model.AppPostLike;

public interface IAppPostService {

	public List<AppPostDto> getAllPost();

	public void likeForPost(AppPostLikeDto postLikeDto) throws Exception;

	public List<AppPostLike> getAllLikeByPostId(Long postId) throws Exception;

	public void createPost(AppPostDto post, MultipartFile file) throws IOException;

	List<AppPostDto> getPostByUserId(Long userId);

}
