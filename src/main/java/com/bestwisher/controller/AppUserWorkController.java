package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileWorkDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.service.IAppUserWorkService;

@RestController
@RequestMapping("/api/work")
public class AppUserWorkController {

	@Autowired
	private IAppUserWorkService userWorkService;

	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> createWork(@RequestBody AppUserProfileWorkDto workDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userWorkService.createWork(workDto);
			response.setData("");
			response.setMessage("Work is created successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> updateWork(@RequestBody AppUserProfileWorkDto workDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userWorkService.updateWork(workDto);
			response.setData("");
			response.setMessage("Work is update successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@DeleteMapping(value = "/delete/{workId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> deleteWork(@PathVariable Long workId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userWorkService.deleteWork(workId);
			response.setData("");
			response.setMessage("Work is delete successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_fetch_user_profile')")
	@GetMapping(value = "/fetch/{userId}")
	public ResponseEntity<MessageResponse<Object>> getAllWork(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserProfileWorkDto> workResponse = userWorkService.getAllWork(userId);
			response.setData(workResponse);
			response.setMessage("Fetch all users work succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

}
