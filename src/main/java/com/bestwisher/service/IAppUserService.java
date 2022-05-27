package com.bestwisher.service;

import java.util.List;
import java.util.Optional;

import com.bestwisher.dto.AppUserDto;
import com.bestwisher.dto.JwtResponse;
import com.bestwisher.exception.UserAlreadyExistAuthenticationException;
import com.bestwisher.model.AppUser;

public interface IAppUserService  {

	AppUser registerUser(AppUserDto user) throws UserAlreadyExistAuthenticationException;

	JwtResponse authenticateUser(AppUserDto user);

	List<AppUser> fetchAllUser();

	Optional<AppUser> disableUser(Long userId);

}
