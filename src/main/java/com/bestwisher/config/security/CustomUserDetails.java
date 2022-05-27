package com.bestwisher.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bestwisher.model.AppPrivilege;
import com.bestwisher.model.AppRoles;
import com.bestwisher.model.AppUser;


public class CustomUserDetails extends AppUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	public CustomUserDetails(final AppUser users) {
		super(users);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(getPrivileges(getRoles()));
	}
	
	private List<String> getPrivileges(Collection<AppRoles> roles) {
		 
        List<String> privileges = new ArrayList<>();
        List<AppPrivilege> collection = new ArrayList<>();
        for (AppRoles role : roles) {
            collection.addAll(role.getPrivilege());
        }
        for (AppPrivilege item : collection) {
        	if(!privileges.contains(item.getPrivilegeKey())) {
        		privileges.add(item.getPrivilegeKey());
        	}
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

}
