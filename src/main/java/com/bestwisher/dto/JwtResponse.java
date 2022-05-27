package com.bestwisher.dto;

import java.util.List;

public class JwtResponse {
	
	public String token;
	public String type = "Bearer";
	public Long id;
	public String username;
	public String email;
	public List<String> privilege;
	public String firstName;
	public String lastName;
	public List<String> roles;
	public String profileImage;

	public JwtResponse(String accessToken, Long id, String username, String email, String firstName, String lastName,
			List<String> privilege, List<String> roles, String profileImage) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.privilege = privilege;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
		this.profileImage = profileImage;
	}
}
