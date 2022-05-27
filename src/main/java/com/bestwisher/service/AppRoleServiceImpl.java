package com.bestwisher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestwisher.dto.AppRoleDto;
import com.bestwisher.model.AppRoles;
import com.bestwisher.repository.AppRoleRepository;

@Service
public class AppRoleServiceImpl implements IAppRoleService {

	@Autowired
	private AppRoleRepository roleRepository;

	@Override
	public List<AppRoles> fetchAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<AppRoles> fetchRoleById(Long roleId) {
		return roleRepository.findById(roleId);
	}

	@Override
	public AppRoles updateRoleDetailById(AppRoleDto appRoleDto) {
		Optional<AppRoles> appRole = roleRepository.findById(appRoleDto.getId());
		if (appRole.isPresent() && !appRole.get().getRole().equalsIgnoreCase(appRoleDto.getRole())) {
			AppRoles role = appRole.get();
			role.setRole(appRoleDto.getRole());
			return roleRepository.save(role);
		} else if (appRole.isPresent()) {
			throw new RuntimeException("Role already exist");
		} else {
			throw new RuntimeException("Role not exist");
		}
	}

	@Override
	public AppRoles createRole(AppRoleDto appRoleDto) {
		AppRoles approle = roleRepository.getByRole(appRoleDto.getRole());
		if (approle != null) {
			throw new RuntimeException("Role already exists");
		}
		if (approle != null && approle.getRole().equalsIgnoreCase(appRoleDto.getRole())) {
			throw new RuntimeException("Role Name already exist");
		}
		AppRoles role = new AppRoles();
		role.setRole(appRoleDto.getRole());
		return roleRepository.save(role);
	}

	@Override
	public void deleteRoleById(Long roleId) {
		Optional<AppRoles> appRole = roleRepository.findById(roleId);
		if (appRole.isPresent()) {
			roleRepository.deleteById(appRole.get().getId());
		} else {
			throw new RuntimeException("Role not exist");
		}

	}

}
