package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bestwisher.dto.AppPostDto;
import com.bestwisher.dto.AppPostLikeDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.service.IAppPostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/post")
public class AppUserPostController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAppPostService postService;

	@PreAuthorize("hasAuthority('app_create_user_post')")
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MessageResponse<Object>> createUserPost(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("post") String post) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			AppPostDto postObj = mapper.readValue(post, AppPostDto.class);
			postService.createPost(postObj, file);
			response.setData(post);
			response.setMessage("Created post succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_read_user_post')")
	@GetMapping(value = "/fetch/all/{userId}")
	public ResponseEntity<MessageResponse<Object>> fetchPostByUserId(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			response.setData(postService.getPostByUserId(userId));
			response.setMessage("fetch post by user id "+userId+" succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_read_all_user_post')")
	@GetMapping(value = "/fetch/all")
	public ResponseEntity<MessageResponse<Object>> fetchAllPost() {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppPostDto> postData = postService.getAllPost();
			response.setData(postData);
			response.setMessage("All post fetch succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_like_user_post')")
	@PostMapping(value = "/like")
	public ResponseEntity<MessageResponse<Object>> likePost(@RequestBody AppPostLikeDto postLikeDto) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			postService.likeForPost(postLikeDto);
			response.setData("");
			response.setMessage("Like Post succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_read_all_user_post')")
	@GetMapping(value = "/fetch/like/{postId}")
	public ResponseEntity<MessageResponse<Object>> fetchLikeByPostId(@PathVariable Long postId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			response.setData(postService.getAllLikeByPostId(postId).stream().map(postLike -> modelMapper.map(postLike, AppPostLikeDto.class))
					.collect(Collectors.toList()));
			response.setMessage("All like for post id "+ postId + "fetch succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
}