package com.sp.repos;

import org.springframework.security.core.userdetails.UserDetails;

import com.sp.entities.SchoolPakUserDetails;

@SuppressWarnings("rawtypes")
public class HibernateUserRepository extends HibernateGenericRepository implements UserRepository{

	public UserDetails loadUserByUsername(String username) {
		return hibernateTemplate.get(SchoolPakUserDetails.class, username);
	}

}
