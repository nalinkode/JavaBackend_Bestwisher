package com.bestwisher.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_role", schema = "app")
public class AppRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20)
	private String role;

	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private Collection<AppUser> users;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(schema = "app", name = "app_role_privileges", joinColumns = {
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = true) }, inverseJoinColumns = {
	@JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID", nullable = true) })
	@JsonManagedReference
	private Collection<AppPrivilege> privilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<AppUser> users) {
		this.users = users;
	}

	public Collection<AppPrivilege> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Collection<AppPrivilege> privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", role=" + role + "]";
	}

}
