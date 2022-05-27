package com.bestwisher.controller;

import java.util.HashMap;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestwisher.dto.AppUserDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.model.AppUser;
import com.bestwisher.service.IAppUserService;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

	@Autowired
	private IAppUserService userManagementService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("hasAuthority('app_read_user_records')")
	@GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchUser() {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			response.setData(userManagementService.fetchAllUser().stream().map(user -> modelMapper.map(user, AppUserDto.class))
					.collect(Collectors.toList()));
			response.setMessage("All user is fetch successfully");
			httpStatus = HttpStatus.OK;
		}
		catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_disable_user_records')")
	@PostMapping(value = "/disable/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> disableUser(@PathVariable Long userId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			Optional<AppUser> disableUser = userManagementService.disableUser(userId);
			response.setData(modelMapper.map(disableUser.get(), AppUserDto.class));
			response.setMessage("User is disbaled successfully");
			httpStatus = HttpStatus.OK;
		}  
		catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
}
