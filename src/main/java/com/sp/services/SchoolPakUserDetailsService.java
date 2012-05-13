package com.sp.services;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sp.entities.SchoolPakUserDetails;
import com.sp.exceptions.DuplicateUsernameException;
import com.sp.repos.UserRepository;

@SuppressWarnings("unchecked")
public class SchoolPakUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException, DataAccessException {
		return userRepository.loadUserByUsername(username);
	}
	
	public void createUser(SchoolPakUserDetails user)
	{
		if(userRepository.loadUserByUsername(user.getUsername()) != null)
		{
			throw new DuplicateUsernameException("User already exists.");
		}
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		userRepository.save(user);
	}

}
