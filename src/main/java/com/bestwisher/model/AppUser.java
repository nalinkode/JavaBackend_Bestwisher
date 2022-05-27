package com.bestwisher.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "app_user", schema = "app")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;
	private String password;
	private boolean enabled;
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	private String gender;

	private Date createdDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(schema = "app", name = "app_user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = true) })
    @JsonManagedReference
	private Collection<AppRoles> roles;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonBackReference
	private Set<AppUserPost> post;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
    private AppUserProfile userProfile;
	
	public AppUser() {
	}

	public AppUser(AppUser user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.phoneNumber = user.getPhoneNumber();
		this.dob = user.getDob();
		this.createdDate = user.getCreatedDate();
		this.gender = user.getGender();
		this.roles = user.getRoles();
		this.id = user.getUserId();
	}

	public AppUser(Long userId, String firstName, String lastName, String username, String email, String password,
			boolean enabled, String phoneNumber, Date dob, Date createdDate, Set<AppRoles> roles, String gender) {
		super();
		this.id = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.createdDate = createdDate;
		this.roles = roles;
		this.gender = gender;
	}

	public Long getUserId() {
		return id;
	}

	public void setUserId(Long userId) {
		this.id = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<AppRoles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRoles> roles) {
		this.roles = roles;
	}
	
	public Collection<AppUserPost> getPost() {
		return post;
	}

	public void setPost(Set<AppUserPost> post) {
		this.post = post;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public AppUserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(AppUserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", phoneNumber="
				+ phoneNumber + ", dob=" + dob + ", gender=" + gender + ", createdDate=" + createdDate + ", roles="
				+ roles + ", post=" + post + "]";
	}
}
