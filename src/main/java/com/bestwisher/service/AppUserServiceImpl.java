package com.bestwisher.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bestwisher.config.security.CustomUserDetails;
import com.bestwisher.config.security.jwt.JWTUtils;
import com.bestwisher.dto.AppUserDto;
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.dto.JwtResponse;
import com.bestwisher.exception.UserAlreadyExistAuthenticationException;
import com.bestwisher.model.AppRoles;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserProfile;
import com.bestwisher.repository.AppRoleRepository;
import com.bestwisher.repository.AppUserProfileRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements IAppUserService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private PasswordEncoder bcrpyptPasswordEncoder;

	@Autowired
	private AppRoleRepository roleRepository;
	
	@Autowired
	private AppUserProfileRepository userProfileRepository;

	@Autowired
	JWTUtils jwtUtils;
	
	@Autowired
	private IAppUserProfileService userProfileService;

	@Override
	public AppUser registerUser(AppUserDto user) throws UserAlreadyExistAuthenticationException {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User Already exists");
		}

		Set<String> strRoles = user.getRole();
		Set<AppRoles> roles = new HashSet<>();
		if (strRoles == null) {
			AppRoles userRole = roleRepository.findByRole("USER")
					.orElseThrow(() -> new RuntimeException("Error: AppRole is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				
				case "SUPERADMIN":
					AppRoles superAdminRole = roleRepository.findByRole("SUPERADMIN")
							.orElseThrow(() -> new RuntimeException("Error: AppRole is not found."));
					roles.add(superAdminRole);

					break;

				case "ADMIN":
					AppRoles adminRole = roleRepository.findByRole("ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: AppRole is not found."));
					roles.add(adminRole);

					break;
					
				default:
					AppRoles userRole = roleRepository.findByRole("USER")
							.orElseThrow(() -> new RuntimeException("Error: AppRole is not found."));
					roles.add(userRole);
				}
			});
		}

		AppUser userEntity = new AppUser();
		AppUserProfile profile = new AppUserProfile();

		String pwd = user.getPassword();
		String encryptpassword = bcrpyptPasswordEncoder.encode(pwd);
		userEntity.setPassword(encryptpassword);
		userEntity.setCreatedDate(new Date());
		userEntity.setDob(user.getDob());
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setUsername(user.getEmail());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		userEntity.setRoles(roles);
		userEntity.setEnabled(true);
		userEntity.setGender(user.getGender());
		
		profile.setCurrentProfileImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/image/profile/file/defaultProfile.jpg").toUriString());
		profile.setCurrentCoverImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/image/profile/file/defaultCoverImage.jpg").toUriString());
		profile.setCreatedDate(new Date());
		profile.setUpdatedDate(new Date());
		profile.setUser(userEntity);
		userEntity.setUserProfile(profile);
		
		return userRepository.save(userEntity);

	}

	@Override
	public JwtResponse authenticateUser(AppUserDto user) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		List<String> privileges = customUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		AppUserProfileDto profile = userProfileService.getUserProfile(customUserDetails.getId());
		String profileImageUrl = null;
		if(profile != null) {
			profileImageUrl = profile.getProfileImage();
		}
		
		List<String> roles = customUserDetails.getRoles().stream().map(item -> item.getRole()).collect(Collectors.toList());

		JwtResponse jwtResponse = new JwtResponse(jwt, customUserDetails.getUserId(), customUserDetails.getUsername(),
				customUserDetails.getEmail(), customUserDetails.getFirstName(), customUserDetails.getLastName(), privileges, roles, profileImageUrl);
		return jwtResponse;
	}

	
	@Override
	public List<AppUser> fetchAllUser() {
		return userRepository.findAll();
	}

	@Override
	public Optional<AppUser> disableUser(Long userId) {
		Optional<AppUser> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setEnabled(false);
			userRepository.save(user.get());
		}
		return user;
	}
}
