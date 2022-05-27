package com.bestwisher.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestwisher.dto.AppUserDto;
import com.bestwisher.dto.JwtResponse;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.exception.UserAlreadyExistAuthenticationException;
import com.bestwisher.model.AppUser;
import com.bestwisher.service.IAppUserService;

@RestController
@RequestMapping("/api/user/auth")
public class AuthenticationController {

	@Autowired
	private IAppUserService userService;

	@PostMapping("/register")
	public ResponseEntity<MessageResponse<Object>> registerUser(@RequestBody AppUserDto user) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppUser successUser = userService.registerUser(user);
			response.setData(successUser);
			response.setMessage("User is register successfully");
			httpStatus = HttpStatus.OK;
		} catch (UserAlreadyExistAuthenticationException e) {
			response.setMessage("User with Email " + user.getEmail() + " already exists");
			response.setData(map);
			httpStatus = HttpStatus.CONFLICT;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> login(@RequestBody AppUserDto user) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			JwtResponse authenticateUser = userService.authenticateUser(user);
			response.setData(authenticateUser);
			response.setMessage("User is authenticated successfully");
			httpStatus = HttpStatus.OK;
		} catch (BadCredentialsException ex) {
			response.setMessage(ex.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		} catch (LockedException ex) {
			// do something
		} catch (DisabledException ex) {
			response.setMessage(ex.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
}
