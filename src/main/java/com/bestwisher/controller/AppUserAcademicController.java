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

import com.bestwisher.dto.AppUserProfileAcademicDto;
import com.bestwisher.dto.AppUserProfileWorkDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.service.IAppUserAcademicService;

@RestController
@RequestMapping("/api/academic")
public class AppUserAcademicController {

	@Autowired
	private IAppUserAcademicService userAcademicService;

	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> createAcademic(@RequestBody AppUserProfileAcademicDto academicDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userAcademicService.createAcademic(academicDto);
			response.setData("");
			response.setMessage("Academic is created successfully");
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
	public ResponseEntity<MessageResponse<Object>> updateAcademic(@RequestBody AppUserProfileAcademicDto academicDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userAcademicService.updateAcademic(academicDto);
			response.setData("");
			response.setMessage("Academic is update successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_create_user_profile')")
	@DeleteMapping(value = "/delete/{academicId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> deleteAcademic(@PathVariable Long academicId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			userAcademicService.deleteAcademic(academicId);
			response.setData("");
			response.setMessage("Academic is delete successfully");
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
	public ResponseEntity<MessageResponse<Object>> getAllAcademic(@PathVariable Long userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserProfileAcademicDto> academicResponse = userAcademicService.getAllAcademic(userId);
			response.setData(academicResponse);
			response.setMessage("Fetch all users academic succussfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

}
