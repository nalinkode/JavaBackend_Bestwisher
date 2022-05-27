package com.bestwisher.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "app_privilege", schema = "app")
public class AppPrivilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String privilegeKey;

	private String privilegeName;

	@ManyToMany(mappedBy = "privilege", cascade = CascadeType.ALL)
	@JsonBackReference
	private Collection<AppRoles> roles;

	public AppPrivilege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<AppRoles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRoles> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrivilegeKey() {
		return privilegeKey;
	}

	public void setPrivilegeKey(String privilegeKey) {
		this.privilegeKey = privilegeKey;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@Override
	public String toString() {
		return "AppPrivilege [id=" + id + ", privilegeKey=" + privilegeKey + ", privilegeName=" + privilegeName + "]";
	}
}
