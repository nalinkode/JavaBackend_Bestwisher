package com.bestwisher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.bestwisher.dto.AppRoleDto;
import com.bestwisher.dto.MessageResponse;
import com.bestwisher.model.AppRoles;
import com.bestwisher.service.IAppRoleService;

@RestController
@RequestMapping("/api/role")
public class AppRoleController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAppRoleService roleService;

	@PreAuthorize("hasAuthority('app_read_all_roles')")
	@GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchAllRole() {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			List<AppRoles> appRole = roleService.fetchAllRole();
			response.setData(
					appRole.stream().map(role -> modelMapper.map(role, AppRoleDto.class)).collect(Collectors.toList()));
			response.setMessage("All Role is fetch successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_read_role_by_id')")
	@GetMapping(value = "/fetch/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> fetchRoleDetailById(@PathVariable Long roleId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			Optional<AppRoles> appRole = roleService.fetchRoleById(roleId);
			AppRoles role = appRole.get();
			response.setData(modelMapper.map(role, AppRoleDto.class));
			response.setMessage("Role is fetch successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_update_role_record')")
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> updateRoleDetailById(@RequestBody AppRoleDto appRoleDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppRoles role = roleService.updateRoleDetailById(appRoleDto);
			response.setData(modelMapper.map(role, AppRoleDto.class));
			response.setMessage("Role is update successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_create_role_record')")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> createRole(@RequestBody AppRoleDto appRoleDto) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			AppRoles role = roleService.createRole(appRoleDto);
			response.setData(modelMapper.map(role, AppRoleDto.class));
			response.setMessage("Role is created successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}

	@PreAuthorize("hasAuthority('app_delete_role_record')")
	@DeleteMapping(value = "/delete/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse<Object>> deleteRoleDetailById(@PathVariable Long roleId) {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpStatus httpStatus = null;
		MessageResponse<Object> response = new MessageResponse<>("", map);
		try {
			roleService.deleteRoleById(roleId);
			response.setData("");
			response.setMessage("Role is deleted successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setData(map);
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<MessageResponse<Object>>(response, httpStatus);
	}
}