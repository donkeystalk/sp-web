package com.sp.repos;

import org.springframework.security.core.userdetails.UserDetails;

import com.sp.entities.SchoolPakUserDetails;

@SuppressWarnings("rawtypes")
public interface UserRepository extends GenericRepository{

	public UserDetails loadUserByUsername(String username);
}
