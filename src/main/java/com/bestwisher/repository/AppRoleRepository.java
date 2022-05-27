package com.bestwisher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppRoles;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRoles, Long> {

	Optional<AppRoles> findByRole(String name);

	AppRoles getByRole(String role);

}