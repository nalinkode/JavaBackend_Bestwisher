package com.bestwisher.service;

import java.util.List;
import java.util.Optional;

import com.bestwisher.dto.AppRoleDto;
import com.bestwisher.model.AppRoles;

public interface IAppRoleService {
	
	List<AppRoles> fetchAllRole();

	Optional<AppRoles> fetchRoleById(Long roleId);

	AppRoles updateRoleDetailById(AppRoleDto appRoleDto);

	AppRoles createRole(AppRoleDto appRoleDto);

	void deleteRoleById(Long roleId);

}
