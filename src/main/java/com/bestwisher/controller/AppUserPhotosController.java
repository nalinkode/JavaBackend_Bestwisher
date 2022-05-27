package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestwisher.dto.AppUserPhotosDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.service.IAppUserPhotosService;

@RestController
@RequestMapping("/api/photo")
public class AppUserPhotosController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAppUserPhotosService userPhotoService;
	
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@GetMapping(value = "/profile/fetch/{userId}")
	public ResponseEntity<MessageResponse<Object>> getAllUsersProfilePhotos(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserPhotosDto> photoResponse = userPhotoService.getAllUserProfilePhotos(userId);
			response.setData(photoResponse);
			response.setMessage("Fetch all users profile photo succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@GetMapping(value = "/cover/fetch/{userId}")
	public ResponseEntity<MessageResponse<Object>> getAllUsersCoverPhotos(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserPhotosDto> photoResponse = userPhotoService.getAllUserCoverPhotos(userId);
			response.setData(photoResponse);
			response.setMessage("Fetch all users cover photo succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@PutMapping(value = "update")
	public ResponseEntity<MessageResponse<Object>> updateUsersProfileOrCoverPhotos(@RequestBody AppUserPhotosDto photoDto) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userPhotoService.updatePhoto(photoDto);
			response.setData("");
			response.setMessage(photoDto.getType()+ " Photo updated successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@DeleteMapping(value = "delete/{type}/{profileId}/photoId/{photoId}")
	public ResponseEntity<MessageResponse<Object>> deleteUsersProfileOrCoverPhotos(@PathVariable String type, @PathVariable Long profileId, @PathVariable Long photoId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userPhotoService.deletePhoto(type,profileId, photoId);
			response.setData("");
			response.setMessage(type+ " Photo deleted successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

}
