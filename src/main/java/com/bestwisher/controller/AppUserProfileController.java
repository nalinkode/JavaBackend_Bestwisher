package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.service.IAppUserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/profile")
public class AppUserProfileController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAppUserProfileService userProfileService;

	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MessageResponse<Object>> createUserProfile(
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
			@RequestPart("userProfile") String userProfile) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {

			ObjectMapper mapper = new ObjectMapper();
			AppUserProfileDto userProfileFromUi = mapper.readValue(userProfile, AppUserProfileDto.class);
			userProfileService.createUserProfile(userProfileFromUi, profileImage, coverImage);
			response.setData(userProfileFromUi);
			response.setMessage("Created user profile succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@PutMapping(value = "/update/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MessageResponse<Object>> UpdateUserImage(
			@RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
			@RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
			@RequestPart("userProfile") String userProfile) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {

			ObjectMapper mapper = new ObjectMapper();
			AppUserProfileDto userProfileFromUi = mapper.readValue(userProfile, AppUserProfileDto.class);
			userProfileService.updateUserProfileImages(userProfileFromUi, profileImage, coverImage);
			response.setData(userProfileFromUi);
			response.setMessage("profile user image updated succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@GetMapping(value = "/fetch/user/{userId}")
	public ResponseEntity<MessageResponse<Object>> getUserProfile(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppUserProfileDto profileResponse = userProfileService.getUserProfile(userId);
			response.setData(profileResponse);
			response.setMessage("Fetch user profile succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@GetMapping(value = "/fetch/all/{userId}")
	public ResponseEntity<MessageResponse<Object>> getAllUsersProfile(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppFriendshipDto> profileResponse = userProfileService.getAllUserProfile(userId);
			response.setData(profileResponse);
			response.setMessage("Fetch all users profile succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
}
