package com.gov.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gov.student.config.JwtUserFactory;
import com.gov.student.core.User;
import com.gov.student.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsername");

		List<User> userList = userRepository.findByUsername(username);
		
		
    	if (!userList.isEmpty()) {
    			return JwtUserFactory.create(userList.get(0), userList.get(0).getPassword());
    		}
    	
    	throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));    	    
	
	}

}
