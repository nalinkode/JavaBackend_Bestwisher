package com.bestwisher.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bestwisher.model.AppUser;
import com.bestwisher.repository.AppUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
	@Autowired
	private AppUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> optionalUser = userRepository.findByUsername(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optionalUser.map(CustomUserDetails::new).get();
	}
}
