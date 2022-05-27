package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.model.AppFriendship;
import com.bestwisher.service.IAppFriendshipService;

@RestController
@RequestMapping("/api/friendship")
public class AppFriendshipController {

	@Autowired
	private IAppFriendshipService friendshipService;

	@Autowired
	private ModelMapper modelMapper;

	@PreAuthorize("hasAuthority('app_add_friendship')")
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> addFriendship(@RequestBody AppFriendshipDto friendshipDto) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppFriendship friendship = friendshipService.addFriend(friendshipDto);
			response.setData(modelMapper.map(friendship, AppFriendshipDto.class));
			response.setMessage("Friend Request sent successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_confirmed_friendship')")
	@PutMapping(value = "/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> confirmedFriendship(@RequestBody AppFriendshipDto friendshipDto) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppFriendship friendship = friendshipService.confirmedFriendshipRequest(friendshipDto);
			response.setData(modelMapper.map(friendship, AppFriendshipDto.class));
			response.setMessage("Friend Request confirmed successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_read_friend_request')")
	@GetMapping(value = "/fetch/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchFriendRequest(@PathVariable Long userId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserProfileDto> friendshipRequestUser = friendshipService.fetchFriendRequest(userId);
			response.setData(friendshipRequestUser);
			response.setMessage("All Friend Request is fetch successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_read_friend_request')")
	@GetMapping(value = "/fetch/pending/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchPendingFriendRequest(@PathVariable Long userId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserProfileDto> friendshipRequestUser = friendshipService.fetchPendingFriendRequest(userId);
			response.setData(friendshipRequestUser);
			response.setMessage("All Pending Friend Request is fetch successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	
	@PreAuthorize("hasAuthority('app_read_friend_request')")
	@GetMapping(value = "/fetch/friends/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchUserFriends(@PathVariable Long userId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppUserProfileDto> friendshipRequestUser = friendshipService.fetchUsersFriends(userId);
			response.setData(friendshipRequestUser);
			response.setMessage("All Pending Friend Request is fetch successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
	


	@PreAuthorize("hasAuthority('app_read_friend_request')")
	@DeleteMapping(value = "/delete/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> deleteFriendRequest(@PathVariable Long friendshipId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			friendshipService.deleteFriendRequest(friendshipId);
			response.setData("");
			response.setMessage("Friend request is cancel successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

}
