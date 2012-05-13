package com.sp.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sp.annotations.FieldMatch;

@Entity
@FieldMatch.List({
	@FieldMatch(first="password", second="confirmPassword", message="Passwords must match.")
})
public class SchoolPakUserDetails implements UserDetails {

	private static final long serialVersionUID = 5741773999415269564L;
	
	@Id
	private String username;
	private String password;
	@Transient
	private String confirmPassword;
	
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval=false, cascade=CascadeType.ALL)
	@JoinColumn(name="username", insertable=true, updatable=false)
	private List<SchoolPakAuthority> authorities;
	
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> retVal = new ArrayList<GrantedAuthority>();
		retVal.addAll(authorities);
		return retVal;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<SchoolPakAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
